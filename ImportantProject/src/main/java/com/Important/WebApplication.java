package com.Important;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.Important.mapper")
@EnableAsync
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class,args);
    }
}
