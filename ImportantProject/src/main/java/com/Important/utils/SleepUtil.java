package com.Important.utils;

public class SleepUtil {
    public static void  sleep(Integer   time) {
        try {
            Thread.sleep(1000*time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
