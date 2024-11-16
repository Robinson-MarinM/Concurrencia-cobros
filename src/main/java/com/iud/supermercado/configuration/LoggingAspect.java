package com.iud.supermercado.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.iud.supermercado..*(..))")
    public void logThreadName() {
        System.out.println("Request is handled by thread: " + Thread.currentThread().getName());
    }
}
