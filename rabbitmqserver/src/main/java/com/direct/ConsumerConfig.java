package com.direct;

import com.DirectMQConfig;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 描述: 消费者监听器
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:10:13 15:33:27
 */
@Component
public class ConsumerConfig {

    @RabbitListener(queues = "testDirectQueue")
    public void consume(Object msg){
        System.out.println("收到消息："+msg);
    }

//    @RabbitListener(queues = DirectMQConfig.ITEM_QUEUE)
//    public void consume1(Object msg){
//        System.out.println("收到消息："+msg);
//    }
//
//    @RabbitListener(queues = DirectMQConfig.ITEM_QUEUE,ackMode = "MANUAL")
//    public void consume2(@Payload byte[] content, @Headers Map<String, Object> headers, Channel channel){
//        System.out.println("收到消息："+content.toString());
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = DirectMQConfig.ITEM_QUEUE, durable = "true"),
//            exchange = @Exchange(name = DirectMQConfig.ITEM_TOPIC_EXCHANGE, durable = "true", type = ExchangeTypes.DIRECT),
//            key = "testDirectRouting"
//    ),ackMode = "MANUAL")
//    @RabbitHandler
//    public void onStockouRestHandleMessage(@Payload Object content, @Headers Map<String, Object> headers, Channel channel)
//            throws IOException {
//        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        boolean multiple = false;
//        try {
//            String s = JSONObject.toJSONString(content);
//            System.out.println("收到消息："+s);
//            channel.basicReject(deliveryTag, true);
//            Thread.sleep(30000);
//            //channel.basicAck(deliveryTag, multiple);
//        }catch (Exception e) {
//        }
//    }

    @RabbitListener(queues = DirectMQConfig.ITEM_QUEUE,ackMode = "MANUAL")
    @RabbitHandler
    public void onStockouRestHandleMessage1(@Payload Object content, @Headers Map<String, Object> headers, Message message, Channel channel)
            throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            String s = JSONObject.toJSONString(message);
            System.out.println("收到消息："+s);
            Boolean redelivered = message.getMessageProperties().getRedelivered();
            if (redelivered){
                channel.basicReject(deliveryTag, false);//拒绝
            }else {
                channel.basicAck(deliveryTag,true);//确认
            }
            System.out.println(redelivered);
            Thread.sleep(3000);
        }catch (Exception e) {
        }
    }

    //模拟延迟消息
    @RabbitListener(queues = "order.release.order.queue")
    public void listener( @Headers Map<String, Object> headers,Channel channel, Message msg) throws IOException {
        System.out.println("收到过期的订单信息:"+msg.getBody().toString());
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false); //手动签收
    }
}
