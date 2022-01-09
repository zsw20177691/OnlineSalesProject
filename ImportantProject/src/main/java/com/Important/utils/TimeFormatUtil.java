package com.Important.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 获得理想当中的时间参数格式
 */
public class TimeFormatUtil {

    public static LocalDateTime nowDateTime(){
        LocalDateTime now = LocalDateTime.now();
        String replace = LocalDateTime.now().toString().replace("T", " ");
        LocalDateTime parse = LocalDateTime.parse(replace.substring(0, replace.lastIndexOf(".")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return parse;
    }
}
