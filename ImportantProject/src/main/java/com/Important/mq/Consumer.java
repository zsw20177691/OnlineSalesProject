package com.Important.mq;

import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    //创建队列名称名字要一致
    public static  final String QUEUE_NAME ="hello";

  public static void main(String[] args) {
    //创建连接工厂
      ConnectionFactory connectionFactory = new ConnectionFactory();
      //工厂IP连接rabbitmq的队列,指定mqIp，用户名，密码
      connectionFactory.setHost("127.0.0.1");
      connectionFactory.setUsername("admin");
      connectionFactory.setPassword("123");
      //创建信道
      try {
          Connection connection = connectionFactory.newConnection();
          //创建交换机
          Channel channel = connection.createChannel();
      /** 消费者消费消息 1:消费哪个队列 2：消费成功之后是否自动应答 3：成功消费的回调 4:消费者取消 消费回调 */
            System.out.println("c1等待接收消息。。。。。");
          //声明 message 消息内容,接收消息
          DeliverCallback deliverCallback=(consumerTag,message)->{
              System.out.println(new String(message.getBody()));
          };
          //取消消息时的回调
          CancelCallback cancelCallback=consumerTag->{
              System.out.println("消息消费被中断");
          };
          channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
