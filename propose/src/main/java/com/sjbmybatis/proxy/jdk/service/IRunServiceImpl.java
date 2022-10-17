package com.sjbmybatis.proxy.jdk.service;

import org.springframework.stereotype.Service;

/**
 * 描述: IRunServiceImpl
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:24 15:20:23
 */
@Service
public class IRunServiceImpl implements IRunService {
    @Override
    public void run() {
        System.out.println("跑步中.....");
    }

    @Override
    public String eat() {
        System.out.println("先吃饭");
        return "再跑步";
    }

    @Override
    public void sleep() {
        System.out.println("该睡觉了.....");
        int i = 1 / 0;
    }
}
