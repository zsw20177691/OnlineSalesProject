package com.Important.eigh;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class Producer {
    //设置普通交换机
    public static final String NORMAL_EXCHANGE ="nomal_exchange";
  public static void main(String[] args)throws Exception {
      Channel channel = RabbitMqUtil.getChannel();

      //死信消息，设置ttl时间，设置延迟消息
      AMQP.BasicProperties build = new AMQP.BasicProperties.Builder().expiration("10000").build();
      for (int i=0;i<=10;i++){
          String    message="hello"+i;
          channel.basicPublish(NORMAL_EXCHANGE,"zhangshan",build,message.getBytes());
      }


  }
}
