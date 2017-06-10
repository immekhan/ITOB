package com.bwa.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //todo Aspect is working fine but it is not printing logs on logging file
    /*private static final Logger LOG = LoggerFactory
            .getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.bwa.controllers.*.*(..))")
    private void beforePointCut(){
        LOG.info("Entering method : ");
    }

    @Before("beforePointCut()")
    public void logBefore(JoinPoint joinPoint) {
//        System.out.println("Hello World");
//        System.out.println("Entering method : "+joinPoint.getSignature().getName());
//        System.out.println("joinPoint.getSignature().getClass(): "+joinPoint.getSignature().getClass());
        LOG.info("Entering method : "+joinPoint.getSignature().getName());
    }*/
}
