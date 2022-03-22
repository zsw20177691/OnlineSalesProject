package com.Important.file;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 负责消息接收
 * @author
 */
public class ReceiveLogs01 {
    //交换机名称
    public static   final String    EXCHANOE_NAME="logs";
  public static void main(String[] args) throws Exception{
    //创建mq连接
      Channel channel = RabbitMqUtil.getChannel();
      //创建交换机
      channel.exchangeDeclare(EXCHANOE_NAME,"fanout");
      //声明一个队列。声明临时队列,队列名称是随机的，当消费者断开队列的时候队列就消失了
      String queue = channel.queueDeclare().getQueue();
      //绑定交换机与队列
      channel.queueBind(queue,EXCHANOE_NAME,"add");
    System.out.println("等待接收消息，把接收到的消息打印屏幕上。。。。");
    //接收消息

      DeliverCallback deliverCallback=(consumerTag,Delivery)->{
          System.out.println("00001控制台打印接收到的消息"+new String(Delivery.getBody()));
      };
      //当消费者取消消息触发的
      CancelCallback cancelCallback= consumerTag->{
          System.out.println("消息消费被中断");
      };
    channel.basicConsume(queue,true,deliverCallback,cancelCallback);
  }
}
