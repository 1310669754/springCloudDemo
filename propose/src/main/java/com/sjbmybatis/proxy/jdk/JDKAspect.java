package com.sjbmybatis.proxy.jdk;

import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 描述: JDKAspect:基于JDK动态代理的切面类
 * 注： JDK代理：底层是反射 ，只能是代理接口，CGLib：可以是类，底层继承
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:06:24 15:13:18
 */
@EnableAspectJAutoProxy
@Component
@Aspect
public class JDKAspect {

    @Pointcut("execution(* com.sjbmybatis.proxy.jdk.service.*.*(..))")
    private void pointCutMethod(){}

    /**
     * 描述: 环绕通知
     * 备注:
     * @author sjb
     * @date 2022/6/24 15:26:00
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = joinPoint.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    @Before("pointCutMethod()")
    public void doBefore(){
        System.out.println("前置通知");
    }

    @AfterReturning(pointcut = "pointCutMethod()",returning = "result")
    public void doAfterReturning(Object result){
        System.out.println("后置置通知,结果是:"+ (ObjectUtils.isEmpty(result) ? "null":result.toString()));
    }

    @AfterThrowing(value = "pointCutMethod()",throwing = "e")
    public void doAfterThrowing(Exception e){
        System.out.println("异常通知, 异常AOP被拦截了");
    }

    @After("pointCutMethod()")
    public void doAfter(){
        System.out.println("最终通知.....");
    }


}
