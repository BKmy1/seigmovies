package com.example.seigmovies.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayMQConfig {
    // 延时队列配置类
    // 交换机名称，访问mq可视化界面,添加对应名称的交换机和队列，并进行交换机和队列的绑定。
    public static final String DELAY_EXCHANGE="my-delay-exchange";
    // 队列名称
    public static final String DELAY_QUEUE="my-delay-queue";
    // 路由key
    public static final String DELAY_ROUTING_KEY="my-delay-key";

    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object> args = new HashMap<String,Object>();;
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAY_EXCHANGE,"x-delayed-message",true,false,args);
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE,true,false,false);
    }

    @Bean
    public Binding delayBinding(Queue delayQ,CustomExchange delayE){
        return BindingBuilder.bind(delayQ).to(delayE).with(DELAY_ROUTING_KEY).noargs();
    }

}
