package com.Important.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory reactiveRedisConnectionFactory)throws UnknownError{
        RedisTemplate<String,Object> templete =new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        templete.setKeySerializer(objectJackson2JsonRedisSerializer);
        templete.setConnectionFactory(reactiveRedisConnectionFactory);
        return templete;
    }
}
