package com.Important.eigh;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class C2 {
    //设置死信交换机
    public static final String DEAD_EXCHANGE ="dead_exchange";
    //死信队列
    public static final String  DEAD_QUEUE="dead_queue";

  public static void main(String[] args)throws Exception {
    //
      Channel channel = RabbitMqUtil.getChannel();
      DeliverCallback deliverCallback=(var,var1)->{
          System.out.println(var1+"接收到的消息为");
      };
      CancelCallback cancelCallback=(message)->{
          System.out.println(message+"玩电脑");
      };
      channel.basicConsume(DEAD_QUEUE,true,deliverCallback,cancelCallback);

  }
}
