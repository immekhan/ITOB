package com.bwa.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(0)
public class SessionAspect {

    private static final Logger LOG = Logger.getLogger(SessionAspect.class);

    @Pointcut("execution(* com.bwa.controllers.*.*(..))")
    private void pointCutControllerBeforeAfterExecution(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.login(..))")
    public void pointCutLogin(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.singUp(..))")
    public void pointCutSignUp(){}

    @Pointcut("execution(* com.bwa.controllers.*.*(javax.servlet.http.HttpServletRequest,..))")
    private void pointCutForSessionValidation(){}

    @Pointcut("execution(* com.bwa.controllers.SessionHandler.*(..))")
    private void pointCutHandlerExecution(){}

    @Before("pointCutControllerBeforeAfterExecution() && pointCutForSessionValidation() && !pointCutLogin() && !pointCutSignUp() && !pointCutHandlerExecution()")
    public void validateSession(JoinPoint joinPoint) {
        //todo add logic for session validation and updation
        // get args
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof HttpServletRequest) {

            // downcast and print Account specific stuff
            HttpServletRequest request = (HttpServletRequest) args[0];
            //todo add the remaining logic here -->calling session handler for validation etc
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.bwa.controllers.LoginController.login(..))",
            returning= "result")
    public void logAfterReturningFromLogin(JoinPoint joinPoint, Object result) {

        //todo persist session after successful login
        printMethodSignature("After Exiting Method",(MethodSignature) joinPoint.getSignature());
        LOG.info("Method returned value is : " + result);
        //todo add logic to check the successful login and persist session
    }

    @AfterReturning(
            pointcut = "execution(* com.bwa.controllers.LoginController.logout(..))",
            returning= "result")
    public void logAfterReturningFromLogout(JoinPoint joinPoint, Object result) {

        //todo invalidate persisted session after successful logout
        printMethodSignature("After Exiting Method",(MethodSignature) joinPoint.getSignature());
        LOG.info("Method returned value is : " + result);
    }

    private void printMethodSignature(String arg , MethodSignature methodSign){
        LOG.info(arg+" : " + methodSign);
    }
}
