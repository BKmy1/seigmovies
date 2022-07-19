package com.example.seigmovies.mq;

import com.example.seigmovies.config.DelayMQConfig;
import com.example.seigmovies.entity.Danmuku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author admin
 * 消息发送者
 */
@Component
public class DelayMqSender {

    private static Logger log = LoggerFactory.getLogger(DelayMqSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 接收用户发送的弹幕，延迟执行的时间
     * @param danmuku
     * @param times
     */
    public void saveDanmuku(Danmuku danmuku,int times){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("延时队列发送消息，延迟："+times/1000+"秒 执行，接收参数为："+danmuku.toString()+"\n"+"=======>发送时间："+date.format(new Date()));
        // convertSendAndReceive同步消费者，convertAndSend立刻发送消息不会等待
        rabbitTemplate.convertAndSend(
                DelayMQConfig.DELAY_EXCHANGE,
                DelayMQConfig.DELAY_ROUTING_KEY,
                danmuku,
                message -> {
                    // 注意这里时间可以用long，而且是设置header
                    message.getMessageProperties().setHeader("x-delay",times);
                    message.getMessageProperties().setDelay(times);
                    return message;
                }
        );
    }
}
