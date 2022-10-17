package com.example.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * 描述: BaseGloble
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:07:08 11:19:55
 */
public class BaseGlobal {

    @Value("spring.datasource.url")
    public static  String datasourceUrl;


    @Value("spring.datasource.username")
    public static  String username;


    @Value("spring.datasource.password")
    public static  String password;


}
