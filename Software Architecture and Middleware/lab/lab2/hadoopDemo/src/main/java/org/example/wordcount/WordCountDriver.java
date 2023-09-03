package org.example.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // get job
        Job job = Job.getInstance(new Configuration());
        // set jar path
        job.setJarByClass(WordCountDriver.class);
        // Associate mapper and reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // Set key and value types for mapper output
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // Set key and value types for finally output
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // set input path and output path
        FileInputFormat.setInputPaths(job, new Path("/home/lsxuan/words"));  // 修改路径
        FileOutputFormat.setOutputPath(job, new Path("/home/lsxuan/output"));   // 修改路径
//        FileInputFormat.setInputPaths(job, new Path(args[0]));   // use input args
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // submit job
        boolean result = job.waitForCompletion(true);

        System.out.println("result:" + result);
    }
}
