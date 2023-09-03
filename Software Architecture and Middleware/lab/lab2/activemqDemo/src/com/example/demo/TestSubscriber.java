package com.example.demo;

import java.util.Arrays;
import java.util.Collections;

public class TestSubscriber {
    public static void main(String[] args) {
        String topic1 = "sam";
        String topic2 = "se";
        MySubscriber subscriber1 = new MySubscriber("subscriber1", Collections.singletonList(topic1));
        MySubscriber subscriber2 = new MySubscriber("subscriber2",Collections.singletonList(topic2));
        MySubscriber subscriber3 = new MySubscriber("subscriber3", Arrays.asList(topic1,topic2));
        new Thread(subscriber1::getMessages).start();
        new Thread(subscriber2::getMessages).start();
        new Thread(subscriber3::getMessages).start();
    }
}
