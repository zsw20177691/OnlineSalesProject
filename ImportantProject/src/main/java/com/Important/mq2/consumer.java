package com.Important.mq2;

import com.Important.utils.RabbitMqUtil;
import com.Important.utils.SleepUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 创建两个消费者
 */
public class consumer {
    //声明队列
    public static  final String QUEUE_NAME="hello";

  public static void main(String[] args)throws Exception {
    //创建队列
      Channel channel = RabbitMqUtil.getChannel();
      //创建队列信息接手之后的回调
      DeliverCallback deliverCallback=(consumerTag,message)->{
          System.out.println("收到的信息为"+new String(message.getBody()));
          //创建信息手动相应
          SleepUtil.sleep(300);
          channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
      };
      CancelCallback cancelCallback=message->{
          System.out.println("信息发送失败");
      };
      channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
  }
}
