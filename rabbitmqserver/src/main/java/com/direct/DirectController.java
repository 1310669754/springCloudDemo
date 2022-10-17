package com.direct;

import com.DirectMQConfig;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 描述: DirectController
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:10:13 15:02:13
 */
@RestController
@RequestMapping("/direct")
public class DirectController {


    public static Logger logger = LoggerFactory.getLogger("DirectController");

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct  //当前类对象创建完成后, 执行这个方法
    public void initTemplate(){
        //注意这个方法是, 只要消息抵达到了broker, ack = true, 服务器收到消息就回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             * @param correlationData   当前消息的唯一关联数据, CorrelationData.id , 可以直接理解为这个消息的整体的唯一id
             * @param ack  消息是否成功收到
             * @param cause  失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                logger.info("消息生产确认!!!!correlationData:{},ack:{},cause:{} ",correlationData,ack,cause);
            }
        });

        //设置消息抵达队列的确认回调, 就是broker内, 交换机到队列的过程
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            //只要消息没有投递给指定的队列, 就会触发, 这个类似于一个失败回调
            /**
             * @param message  投递失败的消息, 的详细信息
             * @param replyCode  回复的状态码
             * @param replyText  回复的文本内容
             * @param exchange   当时这个消息发送给那个交换机
             * @param routingKey   当时这个消息用的哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                logger.error("交换机内部投递失败, message:{},replayCode:{},replayText:{}, routingKey:{}",message,replyCode,replyText,exchange,routingKey);
            }
        });
    }

    //测试direct普通消息的发送
    @PostMapping("/produce")
    public void send(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(DirectMQConfig.ITEM_TOPIC_EXCHANGE, "testDirectRouting", JSONObject.toJSONString("abc"),new CorrelationData(UUID.randomUUID().toString()));

    }

    //模拟延迟消息的发送
    @PostMapping("/produceDelayMessage")
    public void produceDelayMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", JSONObject.toJSONString("延迟消息来咯"),new CorrelationData(UUID.randomUUID().toString()));

    }
}
