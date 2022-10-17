package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述:
 * 备注: 一定要注意 启动类所在的路径，否则会造成扫不到rest包
 * @author sjb
 * @date 2022/6/24 10:48:00
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class Propose1Application {

    public static void main(String[] args) {
        SpringApplication.run(Propose1Application.class, args);
    }

}
