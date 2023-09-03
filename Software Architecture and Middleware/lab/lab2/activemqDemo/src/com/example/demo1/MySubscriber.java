package com.example.demo1;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MySubscriber implements Runnable {


    TopicConnection connection;
    TopicSession session;
    TopicSubscriber subscriber;

    @Override
    public void run() {
        try {
            //创建连接工厂
            TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
            //通过工厂创建连接
            connection = connectionFactory.createTopicConnection();
            //启动连接
            connection.start();
            //创建 session 会话
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建消息队列，是 topic 模型
            Topic topic = session.createTopic("topic_test");
            //创建消息订阅者
            subscriber = session.createSubscriber(topic);
            //接受消息，会阻塞线程
            Message message = subscriber.receive();
            //输出消息
            String msg = ((TextMessage)message).getText();
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (null != subscriber)
                    subscriber.close();
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