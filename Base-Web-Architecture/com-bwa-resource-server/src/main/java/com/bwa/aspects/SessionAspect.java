package com.bwa.aspects;

import com.bwa.business.IUserLogic;
import com.bwa.business.handler.ISessionHandlerLogic;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(0)
public class SessionAspect {

    private static final Logger LOG = Logger.getLogger(SessionAspect.class);

    @Autowired private ISessionHandlerLogic sessionHandlerLogic;
    @Autowired private IUserLogic userLogic;

    @Pointcut("execution(* com.bwa.controllers.*.*(..))")
    private void pointCutControllerBeforeAfterExecution(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.login(..))")
    public void pointCutLogin(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.singUp(..))")
    public void pointCutSignUp(){}

    @Pointcut("execution(* com.bwa.controllers.*.*(javax.servlet.http.HttpServletRequest,..))")
    private void pointCutForSessionValidation(){}

    @Pointcut("execution(* com.bwa.controllers.HomeController.getDepartCount(..))")
    private void pointCutForThrowException(){}

//    @Pointcut("execution(* com.bwa.controllers.SessionHandler.*(..))")
//    private void pointCutHandlerExecution(){}

//    @Before("pointCutControllerBeforeAfterExecution() && pointCutForSessionValidation() && !pointCutLogin() && !pointCutSignUp() && !pointCutHandlerExecution()")
    @Before("pointCutControllerBeforeAfterExecution() && pointCutForSessionValidation() && !pointCutLogin() && !pointCutSignUp()")
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

        //httpSession.invalidate();
    }

    @Around("pointCutForThrowException()")
    public Object justThrowException(ProceedingJoinPoint proceedingJoinPoint){
        //todo this POC is done for session handling
        LOG.info("Before invoking getName() method");
        Object value = null;
        try {
//            userLogic.throwException();
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            LOG.info("ERROR : "+e);
        }
        LOG.info("After invoking getName() method. Return value="+value);
        return value;
        //httpSession.invalidate();
    }

    private void printMethodSignature(String arg , MethodSignature methodSign){
        LOG.info(arg+" : " + methodSign);
    }
}
