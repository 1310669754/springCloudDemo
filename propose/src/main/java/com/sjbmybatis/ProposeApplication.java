package com.sjbmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@EnableFeignClients // Feign注解
@MapperScan("com.sjbmybatis.mapper")
public class ProposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProposeApplication.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        context.getBean("");


    }

}
