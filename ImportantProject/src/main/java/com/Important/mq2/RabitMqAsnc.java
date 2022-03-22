package com.Important.mq2;

import com.Important.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class RabitMqAsnc {

  public static void main(String[] args)throws Exception {
    //    //
    String string = UUID.randomUUID().toString();
    Channel channel = RabbitMqUtil.getChannel();
    //创建队列
    channel.queueDeclare(string,true,false,false,null);
    //开启发布确认
    channel.confirmSelect();
    /**
     * 线程安全有序的一个哈希表，适用于高并发下情况下
     */

    /**并发链路队列，安全有序的哈希表
     * 1,序号与消息进行关联
     * 2，轻松批量删除条目，只要给到序号
     * 3，支持高并发 多线程
     */
    ConcurrentSkipListMap<Long, String> longStringConcurrentSkipListMap = new ConcurrentSkipListMap<Long, String>();

    long login = System.currentTimeMillis();
    // 消息监听器 监听成功消息
    ConfirmCallback confirmCallback =
        (var1, var3) -> {
          if (var3){
            ConcurrentNavigableMap<Long, String> longStringConcurrentNavigableMap = longStringConcurrentSkipListMap.headMap(var1);
            longStringConcurrentNavigableMap.clear();
          }else {
            longStringConcurrentSkipListMap.remove(var1);
          }
          System.out.println("确认的消息是"+var1);
        };

    // 消息监听器，监听未成功发送的消息
    ConfirmCallback confirmCallback1 =
        (var1, var3) -> {
          System.out.println("未确认发送的消息"+var1);
        };
    // 参数一 ：监听哪些消息成功，2：监听哪些消息失败
    channel.addConfirmListener(confirmCallback,confirmCallback1);
    int i = 100;
    for (int j=0;j<i;j++){
      String s=j+"";
      channel.basicPublish("",string,null,s.getBytes());
      //并发链路队列 队列，捕获要发送的消息
      longStringConcurrentSkipListMap.put(channel.getNextPublishSeqNo(),s);
      System.out.println("成功发送消息："+j);
    }

    long end = System.currentTimeMillis();
    System.out.println("异步确认 耗时为"+(end-login));
  }
}
