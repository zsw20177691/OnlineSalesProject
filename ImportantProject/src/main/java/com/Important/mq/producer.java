package com.Important.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 你就是下一个大佬
 * 实现发送消息
 */
public class producer {
    //队列名称
    public static  final String QUEUE_NAME="hello";
  // 发送消息
  public static void main(String[] args) {
    //创建连接工厂
      ConnectionFactory connectionFactory = new ConnectionFactory();
      //工厂IP连接rabbitmq的队列,指定mqIp，用户名，密码
      connectionFactory.setHost("127.0.0.1");
      connectionFactory.setUsername("admin");
      connectionFactory.setPassword("123");
      //创建连接
      try{
          Connection connection = connectionFactory.newConnection();
          //创建信道，连接交换机，连接队列
          Channel channel = connection.createChannel();
          /**
           *  创建队列 参数：1:队列名称 2:是否需要持久化  3：该队列是否循序消息共享，让多个消费者进行消费
           *  4:是否自动删除，最后一个消费者端开连接以后，该队列是否自动删除
           *  5:其他参数
           */
          channel.queueDeclare(QUEUE_NAME,true,false,false,null);
          //发送消息
          String s = "hello world";
          /**
           * 1：表示发送到哪个交换机 2：表示路由key，本次是队列名称，3：其他参数信息 4：发送消息实体
           */
          channel.basicPublish("",QUEUE_NAME,null,s.getBytes());
            System.out.println("消息发送完毕");
      }catch (Exception e){
            System.out.println(e);
      }


  }
}
