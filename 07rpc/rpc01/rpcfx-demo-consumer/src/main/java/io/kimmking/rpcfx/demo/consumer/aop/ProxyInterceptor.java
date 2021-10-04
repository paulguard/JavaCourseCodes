package io.kimmking.rpcfx.demo.consumer.aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author by 平向东
 * @date 2021/10/3 11:26 Copyright 2021 北京交个朋友数码科技有限公司. All rights reserved.
 */
@Component
@Aspect
@Slf4j
public class ProxyInterceptor implements Ordered {

    //@Around("execution(* com.company.controller.*.*(..))")
    @Around("execution(* io.kimmking.rpcfx.demo.api.UserService.findById(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        log.error("enter around");

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        try {
            return point.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
