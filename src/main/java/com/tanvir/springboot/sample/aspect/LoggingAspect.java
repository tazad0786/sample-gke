package com.tanvir.springboot.sample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing: " + joinPoint.getSignature().toShortString());
    }
}
