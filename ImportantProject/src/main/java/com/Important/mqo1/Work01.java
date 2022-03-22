package com.Important.mqo1;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 这是一个工作线程（相当于消费者）
 */
public class Work01 {
    //创建队列名称
    public static final String  QUEUE_NAME="hello";

  public static void main(String[] args)throws Exception {
    //
      Channel channel = RabbitMqUtil.getChannel();
      //声明 message 消息内容,接收消息
      DeliverCallback deliverCallback=(consumerTag, message)->{
          System.out.println("消费者接收到的消息"+ new String(message.getBody()));
      };
      //取消消息时的回调
      CancelCallback cancelCallback= consumerTag->{
          System.out.println("消息消费被中断"+consumerTag);
      };
      System.out.println("c1等待接收消息。。。。。");
      channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
  }
}
