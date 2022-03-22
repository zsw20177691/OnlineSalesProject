package com.Important.direct;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

public class EmitLong {

    //交换机名称
    public static   final String    EXCHANOE_NAME="direct_logs";

  public static void main(String[] args) throws Exception {
    //创建信道
      Channel channel = RabbitMqUtil.getChannel();
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNext()){
          String next = scanner.next();
          //进行信道发送
          channel.basicPublish( EXCHANOE_NAME,"info",null   ,next.getBytes());
      System.out.println("生产者者发出消息"+next);
      }
  }
}
