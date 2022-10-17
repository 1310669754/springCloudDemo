package com;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: DirectMQConfig
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:10:13 14:51:30
 */
@SpringBootConfiguration
public class DirectMQConfig {
    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "item_queue";

    /**
     * 描述: 一般设置一下队列的持久化就好,其余两个就是默认false
     * 备注:
     * @author sjb
     * @date 2022/10/13 14:54:00
     */
//    @Bean
//    public Queue directQueue(){
//        return new Queue("testDirectQueue",true);
//    }
//
//    @Bean
//    public Exchange directExchange(){
//        return new DirectExchange("testDirectExchange",true,false);
//    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.directExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }
    @Bean
    public Queue queue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(Queue queue,Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("testDirectRouting").noargs();
    }


    /**
     * 创建死信队列
     * 创建延时队列,
     *  其实就是配置消息什么时候变成死信, 死信之后, 交给那个交换机, 交给那个路由键
     * @return
     */
    @Bean
    public Queue orderDelayQueue(){
        /**
         * 设置消息, 1分钟过期,
         */
        Map<String,Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "order-event-exchange"); //指定死信路由
        args.put("x-dead-letter-routing-key", "order.release.order"); //指定死信路由键
        args.put("x-message-ttl", 60000); // 消息过期时间 1分钟
        Queue queue = new Queue("order.delay.queue", true, false, false,args);
        return queue;
    }
    @Bean
    public Queue orderReleaseQueue(){
        Map<String,Object> args = new HashMap<>();
        Queue queue = new Queue("order.release.order.queue", true, false, false,args);
        return queue;
    }
    @Bean
    public Exchange orderEventExchange(){
        return new TopicExchange("order-event-exchange",true,false);
    }
    /**
     * 设置队列和交换机的绑定关系
     */
    @Bean
    public Binding orderCreateOrderBinging(){
        return new Binding(
                "order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange"
                ,"order.create.order"
                ,null
        );
    }
    @Bean
    public Binding orderReleaseOrderBinging(){
        return new Binding(
                "order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange"
                ,"order.release.order"
                ,null
        );
    }

}
