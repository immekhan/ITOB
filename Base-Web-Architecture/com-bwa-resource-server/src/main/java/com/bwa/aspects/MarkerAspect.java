package com.bwa.aspects;

import com.bwa.util.Constants;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
public class MarkerAspect {

    private static final Logger LOG = Logger.getLogger(MarkerAspect.class);

    @Pointcut("execution(* com.bwa.controllers.*.*(..))")
    private void pointCutControllerBeforeAfterExecution(){}

    @Pointcut("execution(* com.bwa.business.impl.*.*(..))")
    private void pointCutBusinessLogicBeforeAfterExecution(){}

    @Pointcut("execution(* com.bwa.business.handler.impl.*.*(..))")
    private void pointCutBusinessLogicHandlerBeforeAfterExecution(){}

    @Before("pointCutControllerBeforeAfterExecution() || pointCutBusinessLogicBeforeAfterExecution() || pointCutBusinessLogicHandlerBeforeAfterExecution() ")
    public void logBeforeExecution(JoinPoint joinPoint) {
        printMethodSignature(Constants.AOP_MSG_METHOD_BEFORE_ENTERING,(MethodSignature) joinPoint.getSignature());
    }

    @After("pointCutControllerBeforeAfterExecution() || pointCutBusinessLogicBeforeAfterExecution() || pointCutBusinessLogicHandlerBeforeAfterExecution() ")
    public void logAfterExecution(JoinPoint joinPoint) {
        printMethodSignature(Constants.AOP_MSG_METHOD_AFTER_EXITING,(MethodSignature) joinPoint.getSignature());
    }

    private void printMethodSignature(String arg , MethodSignature methodSign){
        LOG.info(arg+" : " + methodSign);
    }
}
