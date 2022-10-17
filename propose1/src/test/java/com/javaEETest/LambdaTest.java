package com.javaEETest;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: LambdaTest
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:07:11 14:42:09
 */
@SpringBootTest
public class LambdaTest {


    @Test
    public void test1(){
        List list = Arrays.asList("1","2");
        list.forEach(System.out::println);

        List<String> list1 = (List<String>) list.stream().filter(o -> o == "1").collect(Collectors.toList());


        System.out.println(list1);
    }


}
