package org.example.logAnalyse;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.StringTokenizer;

public class AnalyseMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final Text outKey = new Text();
    private final IntWritable outValue = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        StringTokenizer tokenizer = new StringTokenizer(value.toString());
        tokenizer.nextToken();
        tokenizer.nextToken();
        tokenizer.nextToken();
        String userId = tokenizer.nextToken();
        outKey.set(userId);
        context.write(outKey,outValue);
    }
}
