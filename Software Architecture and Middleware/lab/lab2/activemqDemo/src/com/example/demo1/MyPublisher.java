package com.example.demo1;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MyPublisher {
    TopicConnection connection;
    TopicSession session;
    TopicPublisher publisher;

    public void publicshTopic() {
        try {
            //创建连接工厂
            TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
            //创建连接
            connection = connectionFactory.createTopicConnection();
            //启动连接
            connection.start();
            //创建会话
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个主题
            Topic topic = session.createTopic("topic_test");
            //创建主题发布者
            publisher = session.createPublisher(topic);
            //创建消息
            Message msg = session.createTextMessage("Topic message");
            //发布消息
            publisher.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (null != publisher)
                    publisher.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
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
}