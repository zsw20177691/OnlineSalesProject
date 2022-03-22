package com.Important.direct;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsDirect02 {
    public static final String EXCHANGE_NAME ="direct_logs";
  public static void main(String[] args) throws Exception{
    //创建信道
      Channel channel = RabbitMqUtil.getChannel();
      //声明一个交换机，捆绑一个定向交换机
      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
      //声明队列
      channel.queueDeclare("disk",false,false,false,null  );
      //绑定交换机,
      channel.queueBind("disk",EXCHANGE_NAME,"erro");
//      channel.queueBind("console",EXCHANGE_NAME,"warning");//告警
      // 接收消息回调接口
    DeliverCallback deliverCallback =
        (consumerTag, message) -> {
          System.out.println("000002控制台打印接收到的消息"+new String(message.getBody()));
        };
    //消费者取消消息时接口回调
      CancelCallback cancelCallback=(consumerTag) -> {
          System.out.println("0001控制台打印没接收到的消息");
      };
      //消费者接收消息
      channel.basicConsume("disk",true,deliverCallback,cancelCallback);
  }
}
