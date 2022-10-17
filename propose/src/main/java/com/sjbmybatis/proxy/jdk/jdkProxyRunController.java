package com.sjbmybatis.proxy.jdk;

import com.sjbmybatis.proxy.jdk.service.IRunService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: RunController
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:24 15:42:07
 */
@RestController
@RequestMapping("jdkProxy")
@Api(tags = "AOP-JDK代理")
public class jdkProxyRunController {

    @Autowired
    private IRunService iRunService;

    @RequestMapping(value = "/run",method = RequestMethod.GET)
    public void run(){
        iRunService.run();
    }

    @RequestMapping(value = "/eat",method = RequestMethod.POST)
    public String eat(@RequestBody String name){
        System.out.println("用户："+name);
       return iRunService.eat();
    }

    @RequestMapping(value = "/sleep",method = RequestMethod.POST)
    public void sleep(@RequestBody String name) {
        System.out.println("用户：" + name);
        iRunService.sleep();
    }




}
