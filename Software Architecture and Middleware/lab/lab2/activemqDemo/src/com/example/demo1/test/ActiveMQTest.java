package com.example.demo1.test;


import com.example.demo1.MyPublisher;
import com.example.demo1.MySubscriber;

public class ActiveMQTest {

    public static void main(String[] args) throws InterruptedException {
//        MyPublisher publisher = new MyPublisher();
//        publisher.publicshTopic();
        MySubscriber subscriber = new MySubscriber();
        Thread t1 = new Thread(subscriber);
        Thread t2 = new Thread(subscriber);
        t1.start();
        t2.start();
        Thread.sleep(4000);
        MyPublisher publisher = new MyPublisher();
        publisher.publicshTopic();
    }
}