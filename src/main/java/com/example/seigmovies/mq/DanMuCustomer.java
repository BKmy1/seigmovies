package com.example.seigmovies.mq;

import com.example.seigmovies.config.DelayMQConfig;
import com.example.seigmovies.entity.Danmuku;
import com.example.seigmovies.service.DanmuKuService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DanMuCustomer {
    private static Logger log = LoggerFactory.getLogger(DanMuCustomer.class);

    @Autowired
    private DanmuKuService danmuKuService;

    @RabbitListener(queues = DelayMQConfig.DELAY_QUEUE)
    public void danMuCustomer(Danmuku danmuku, Message message, Channel channel) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("消费者接收弹幕保存信号开始消费。。。。接收参数为：" + danmuku.toString() + "\n" + "=====>接收时间：" + date.format(new Date()));
        try {
            if (danmuKuService.addDanmu(danmuku) == 1) {
                // 通知 MQ 消息已被接收，可以ACK（从队列中删除）了
                // Shutdown Signal: channel error; protocol method:
                // #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
                // 手动ack
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                // 重新放入队列
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
                // 抛弃此条消息
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            } else {
                log.error("弹幕入库失败：参数：" + danmuku.toString());
            }
        } catch (Exception e) {
            log.error("延迟队列=====> 新增弹幕失败！" + e.getMessage());
        }
    }
}
