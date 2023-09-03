package RDD;

import com.google.common.collect.Iterables;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Macan on 2017/9/26.
 */
public class PageRank implements Serializable {

    public static void main(String[] args) {
        SparkConf sc = new SparkConf().setMaster("local[*]").setAppName("PageRank");
        JavaSparkContext jsc = new JavaSparkContext(sc);

        JavaPairRDD<String,Double> set1= jsc.parallelizePairs(Arrays.asList(new Tuple2<>("1",1.0),new Tuple2<>("2",1.0)));
        JavaPairRDD<String,Double> set2= jsc.parallelizePairs(Arrays.asList(new Tuple2<>("1",3.0)));

        List<Tuple2<String,Double>> sub = set1.subtract(set2).collect();


    }
}
