package com.Important.mqo1;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * 生产者能够发送大量消息
 */
public class task01 {
    //指定队列名称
    public static final String  QUEUE_NAME="hello";

  public static void main(String[] args)throws Exception {
    //
      Channel channel = RabbitMqUtil.getChannel();
      /**
       *  创建队列 参数：1:队列名称 2:是否需要持久化  3：该队列是否循序消息共享，让多个消费者进行消费
       *  4:是否自动删除，最后一个消费者端开连接以后，该队列是否自动删除
       *  5:其他参数
       */
      channel.queueDeclare(QUEUE_NAME,true,false,false,null);
      /**
       * 1：表示发送到哪个交换机 2：表示路由key，本次是队列名称，3：其他参数信息 4：发送消息实体
       */
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNext()){
          String next = scanner.next();
          channel.basicPublish("",QUEUE_NAME,null,next.getBytes());
      System.out.println("消息发送完成"+next.getBytes());
      }

  }
}
