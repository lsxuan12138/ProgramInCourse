package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.*;

public class MySubscriber {
    private Set<String> destinations;
    private String name;

    public MySubscriber(String name, List<String> topics) {
        this.name = name;
        destinations = new HashSet<>();
        destinations.addAll(topics);
    }

    public void subscribe(String topic) {
        destinations.add(topic);
    }

    public void getMessages() {
        //System.out.println(name+"start");
        List<MessageConsumer> subscribers = new ArrayList<>();
        Connection connection = null;
        Session session = null;
        try {
            // 创建连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616");

            // 创建连接
            connection = factory.createConnection();
            //connection.setClientID(this.name);
            connection.start();
            //System.out.println(this.name+" get connection");
            // 创建会话，默认为非事务性会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建多个订阅者，分别订阅不同的主题

            for (String des :
                    destinations) {
                subscribers.add(session.createConsumer(session.createTopic(des)));
            }
            //System.out.println(this.name+" get des");
            // 接收消息

            for (MessageConsumer consumer :
                    subscribers) {
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            if (message == null) System.out.println("Subscriber "+name +" received message from null: null");
                            else if (message instanceof TextMessage) {
                                String text = ((TextMessage) message).getText();
                                System.out.println("Subscriber "+name +" received message from topic " + message.getJMSDestination().toString().substring(8)
                                        + ": " + text);
                            } else {
                                System.out.println("Subscriber "+name +" received message from topic " + message.getJMSDestination().toString().substring(8)
                                        + ": " + message);
                            }
                        } catch (JMSException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            //System.out.println(this.name+" sleep");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (MessageConsumer sub :
                    subscribers) {
                if (sub != null) {
                    try {
                        sub.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                if (null != session)
                    session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != connection)
                        connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
