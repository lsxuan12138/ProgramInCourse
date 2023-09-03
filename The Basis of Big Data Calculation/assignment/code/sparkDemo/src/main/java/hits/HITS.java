package hits;

import com.google.common.base.Optional;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-12-17 22:01
 */
public class HITS {
    private static final Logger LOGGER = Logger.getLogger(HITS.class);
    private static final double MIN_DELTA = 0.0001;

    private static final int MAX_ITERATION = 50;

    public static void main(String[] args) {
        //SparkConf sc = new SparkConf().setMaster("local[*]").setAppName("PageRank");

        SparkConf sc = new SparkConf().setAppName("PageRank");
        JavaSparkContext jsc = new JavaSparkContext(sc);
        jsc.setLogLevel("WARN");

        if (args.length < 2) {
            LOGGER.error("should assigned parameter of page info");
            System.exit(-1);
        }
        JavaRDD<String> data = jsc.textFile(args[0]);

        JavaRDD<String> inIds = data.map((Function<String, String>) s -> s.split("\t")[0]);
        JavaRDD<String> outIds = data.map((Function<String, String>) s -> s.split("\t")[1]);
        JavaPairRDD<String, Double> hub = inIds.union(outIds)
                .mapToPair((PairFunction<String, String, Double>) s -> new Tuple2<>(s, 1.0))
                .distinct();
        JavaPairRDD<String, Double> authority = hub;
//        JavaPairRDD<String,Iterable<String>> in = data.mapToPair(new PairFunction<String, String, String>() {
//            @Override
//            public Tuple2<String, String> call(String s) throws Exception {
//                String[] strings = s.split("\t");
//                return new Tuple2<>(strings[0],strings[1]);
//            }
//        }).groupByKey().cache();
//        JavaPairRDD<String,Iterable<String>> out = data.mapToPair(new PairFunction<String, String, String>() {
//            @Override
//            public Tuple2<String, String> call(String s) throws Exception {
//                String[] strings = s.split("\t");
//                return new Tuple2<>(strings[1],strings[0]);
//            }
//        }).groupByKey().cache();
        JavaPairRDD<String, String> inLinks = data.mapToPair((PairFunction<String, String, String>) s -> {
            String[] strings = s.split("\t");
            return new Tuple2<>(strings[0], strings[1]);
        }).cache();
        JavaPairRDD<String, String> outLinks = data.mapToPair((PairFunction<String, String, String>) s -> {
            String[] strings = s.split("\t");
            return new Tuple2<>(strings[1], strings[0]);
        }).cache();
        for (int i = 0; i < MAX_ITERATION; i++) {
            LOGGER.warn("-------------第次" + (i + 1) + "迭代----------");
            //authority为所有指向它的hub值
            JavaPairRDD<String, Double> tempAuthority = outLinks.join(hub)
                    .mapToPair((PairFunction<Tuple2<String, Tuple2<String, Double>>, String, Double>) stringTuple2Tuple2
                            -> new Tuple2<>(stringTuple2Tuple2._2._1, stringTuple2Tuple2._2._2))
                    .reduceByKey((Function2<Double, Double, Double>) Double::sum);
            tempAuthority = authority.leftOuterJoin(tempAuthority)
                    .mapToPair((PairFunction<Tuple2<String, Tuple2<Double, Optional<Double>>>, String, Double>) stringTuple2Tuple2
                            -> new Tuple2<>(stringTuple2Tuple2._1, stringTuple2Tuple2._2._2.or(0.0)));

            // 求和 标准化
            Double auSum = Math.sqrt(tempAuthority.values()
                    .map((Function<Double, Double>) aDouble -> Math.pow(aDouble, 2.0))
                    .reduce((Function2<Double, Double, Double>) Double::sum));
            JavaPairRDD<String, Double> newAuthority = tempAuthority
                    .mapToPair((PairFunction<Tuple2<String, Double>, String, Double>) stringDoubleTuple2
                            -> new Tuple2<>(stringDoubleTuple2._1, stringDoubleTuple2._2 / auSum));
            //hub为所有它指向的authority值
            JavaPairRDD<String, Double> tempHub = inLinks.join(authority)
                    .mapToPair((PairFunction<Tuple2<String, Tuple2<String, Double>>, String, Double>) stringTuple2Tuple2
                            -> new Tuple2<>(stringTuple2Tuple2._2._1, stringTuple2Tuple2._2._2))
                    .reduceByKey((Function2<Double, Double, Double>) Double::sum);
            tempHub = hub.leftOuterJoin(tempHub)
                    .mapToPair((PairFunction<Tuple2<String, Tuple2<Double, Optional<Double>>>, String, Double>) stringTuple2Tuple2
                            -> new Tuple2<>(stringTuple2Tuple2._1, stringTuple2Tuple2._2._2.or(0.0)));
            Double hubSum = Math.sqrt(tempHub.values()
                    .map((Function<Double, Double>) aDouble -> Math.pow(aDouble, 2.0))
                    .reduce((Function2<Double, Double, Double>) Double::sum));
            JavaPairRDD<String, Double> newHub = tempHub
                    .mapToPair((PairFunction<Tuple2<String, Double>, String, Double>) stringDoubleTuple2
                            -> new Tuple2<>(stringDoubleTuple2._1, stringDoubleTuple2._2 / hubSum));
            //计算改变量
            Double change1 = newAuthority.join(authority)
                    .map((Function<Tuple2<String, Tuple2<Double, Double>>, Double>) stringTuple2Tuple2
                            -> Math.abs(stringTuple2Tuple2._2._1 - stringTuple2Tuple2._2._2))
                    .reduce((Function2<Double, Double, Double>) Double::sum);
            Double change2 = newHub.join(hub)
                    .map((Function<Tuple2<String, Tuple2<Double, Double>>, Double>) stringTuple2Tuple2
                            -> Math.abs(stringTuple2Tuple2._2._1 - stringTuple2Tuple2._2._2))
                    .reduce((Function2<Double, Double, Double>) Double::sum);
            //更新authority
            authority = newAuthority;
            hub = newHub;
            if (change2 + change1 < MIN_DELTA) break;
        }
        authority.mapToPair(new PairFunction<Tuple2<String, Double>, Double, String>() {
            @Override
            public Tuple2<Double, String> call(Tuple2<String, Double> stringDoubleTuple2) throws Exception {
                return new Tuple2<>(stringDoubleTuple2._2, stringDoubleTuple2._1);
            }
        }).sortByKey(false).saveAsTextFile(args[1]);
    }
}
