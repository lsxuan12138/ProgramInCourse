package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyPublisher {
    private final String topic;

    public MyPublisher(String topic) {
        this.topic = topic;
    }

    public void publishMessage(String msg) {
        Connection connection = null;
        Session session = null;
        MessageProducer publisher = null;
        try {
            //创建连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                    "admin",
                    "admin",
                    "tcp://localhost:61616");
            //创建连接
            connection = factory.createTopicConnection();
            //启动连接
            connection.start();
            //创建会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个主题
            Topic topic = session.createTopic(this.topic);
            //创建主题发布者
            publisher = session.createProducer(topic);
            //创建消息
            Message message = session.createTextMessage(msg);
            //发布消息
            publisher.send(message);
            System.out.println("Publishers "+this.topic+" send msg: "+msg);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }finally {
            //释放资源
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
