package com.Important.eigh;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

public class Consumer01 {

    //设置普通交换机
    public static final String NORMAL_EXCHANGE ="nomal_exchange";
    //设置死信交换机
    public static final String DEAD_EXCHANGE ="dead_exchange";

    //普通队列
    public static final String NORMAL_QUEUE ="nomal_queue";
    //死信队列
    public static final String  DEAD_QUEUE="dead_queue";

  public static void main(String[] args) throws Exception{
      Channel channel = RabbitMqUtil.getChannel();//获取信道
      //声明死信和普通交换机的类型未direct
      channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
      channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
      //声明普通队列
      Map<String, Object> stringObjectHashMap = new HashMap<String, Object>();
      //设置过期时间
//      stringObjectHashMap.put("x-message-ttl",10000);
      //正常队列设置死信交换机
      stringObjectHashMap.put("x-dead-letter-exchange",DEAD_EXCHANGE);
      //正常队列设置死信交换机的routingKey
      stringObjectHashMap.put("x-dead-letter-routing-key","lisi");
      channel.queueDeclare(NORMAL_QUEUE,false,false,false,stringObjectHashMap  );
      //跟死信队列
      //////////////////////////////////////////////
      channel.queueDeclare(DEAD_QUEUE,false,false,false,null  );
      //绑定普通的交换机与普通的队列
      channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangshan");
      //绑定死信交换机与死信队列
      channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");
      System.out.println("等待接收消息。。。。");
      DeliverCallback deliverCallback=(consumerTag,message)->{
          System.out.println("正常队列收到的消息为"+message.getBody());
      };
      CancelCallback cancelCallback=(message)->{
          System.out.println("未正常收到消息");
      };
      channel.basicConsume(NORMAL_QUEUE,true,deliverCallback,cancelCallback );
  }
}
