package com.example.springdemo.core.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Aspect
public class AspectConfig {

    @Before(value = "execution(* com.example.springdemo.core.controller.*.*(..))")
    public void BeforeAdvice(JoinPoint joinPoint){
        log.info("Inside Before Advice");
    }
}
