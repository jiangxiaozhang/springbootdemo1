package com.jh.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;


@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();

        //使用Fastjson实现对象的序列化
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置值的序列化方式
        redisTemplate.setValueSerializer(serializer);
        //设置hash类型的数据序列化方式
        redisTemplate.setHashValueSerializer(serializer);
        //设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hash的key的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置redis模板的工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
