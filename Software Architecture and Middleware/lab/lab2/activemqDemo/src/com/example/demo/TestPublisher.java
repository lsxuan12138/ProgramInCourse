package com.example.demo;

import java.util.Arrays;
import java.util.Collections;

public class TestPublisher {

    public static void main(String[] args){
        String topic1 = "sam";
        String topic2 = "se";

        MyPublisher publisher1 = new MyPublisher(topic1);
        MyPublisher publisher2 = new MyPublisher(topic2);


        publisher1.publishMessage("msg 1");
        publisher1.publishMessage("msg 3");
        publisher2.publishMessage("msg 2");
        publisher2.publishMessage("msg 4");


    }
}
