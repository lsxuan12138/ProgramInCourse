package org.example.logAnalyse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;;


import java.io.IOException;

public class AnalyseDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // get job
        Job job = Job.getInstance(new Configuration());
        // set jar path
        job.setJarByClass(AnalyseDriver.class);
        // Associate mapper and reducer
        job.setMapperClass(AnalyseMapper.class);
        job.setReducerClass(AnalyseReducer.class);
        // Set key and value types for mapper output
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // Set key and value types for finally output
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // set input path and output path
        FileInputFormat.setInputPaths(job, new Path(args[0]));   // use input args
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // submit job
        boolean result = job.waitForCompletion(true);

        System.out.println("result:" + result);
    }
}
