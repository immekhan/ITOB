package com.bwa.aspects;

import com.bwa.business.handler.ISessionHandlerLogic;
import com.bwa.controllers.response.ResponseObject;
import com.bwa.exceptions.CustomException;
import com.bwa.util.AppUtils;
import com.bwa.util.CodeConstants;
import com.bwa.util.ControllerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(0)
public class SessionAspect {

    private static final Logger LOG = Logger.getLogger(SessionAspect.class);

    @Autowired private ISessionHandlerLogic sessionHandlerLogic;

    @Pointcut("execution(* com.bwa.controllers.*.*(..))")
    private void pointCutControllerBeforeAfterExecution(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.login(..)) ||  execution(* com.bwa.controllers.LoginController.hasLoggedIn(..))")
    public void pointCutLogin(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.singUp(..))")
    public void pointCutSignUp(){}

    @Pointcut("execution(* com.bwa.controllers.*.*(javax.servlet.http.HttpServletRequest,..))")
    private void pointCutForSessionValidation(){}

    @Pointcut("execution(* com.bwa.controllers.HomeController.getDepartCount(..))")
    private void pointCutForThrowException(){}

    @Pointcut("execution(* com.bwa.controllers.LoginController.logout(..))")
    public void pointCutLogout(){}

    @Around("pointCutControllerBeforeAfterExecution() && pointCutForSessionValidation() && !pointCutLogin() && !pointCutSignUp() && !pointCutLogout()")
    public String validateSessionBeforeCallingRestApi(ProceedingJoinPoint proceedingJoinPoint){

        ResponseObject response =new ResponseObject();
        String jsonResponse="";
        try {
            //fetch parameters
            Object[] args = proceedingJoinPoint.getArgs();
            if (args[0] instanceof HttpServletRequest) {

                HttpServletRequest request = (HttpServletRequest) args[0];
                //validate session
//                sessionHandlerLogic.validatePersisted(request.getSession());
                //proceed with method exicution
                jsonResponse =(String) proceedingJoinPoint.proceed();
            }

        } catch (CustomException e) {

            LOG.info("ERROR : "+e);
            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
            jsonResponse = AppUtils.convertToJson(response);

        } catch (Throwable e){

            LOG.info("ERROR : "+e);
            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
            jsonResponse = AppUtils.convertToJson(response);

        }
        return jsonResponse;
    }

    @Around("pointCutLogin()")
    public String persistSessionAfterSuccessLogin(ProceedingJoinPoint proceedingJoinPoint){

        ResponseObject response =new ResponseObject();
        String jsonResponse="";

        try {
            //fetch parameters
            Object[] args = proceedingJoinPoint.getArgs();
            if (args[0] instanceof HttpServletRequest) {

                HttpServletRequest request = (HttpServletRequest) args[0];
                //proceed with login
                jsonResponse =(String) proceedingJoinPoint.proceed();

                ObjectMapper mapper = new ObjectMapper();
                response=(ResponseObject) mapper.readValue(jsonResponse,ResponseObject.class);

                if(response.getStatus().getCode().equals(CodeConstants.CODE_SUCCESS)){
                    sessionHandlerLogic.persistSession(request.getSession());
                }
            }

        } catch (CustomException e) {

            LOG.info("ERROR : "+e);
            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
            jsonResponse = AppUtils.convertToJson(response);

        } catch (Throwable e){

            LOG.info("ERROR : "+e);
            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
            jsonResponse = AppUtils.convertToJson(response);

        }
        return jsonResponse;
    }

    @Around("pointCutLogout()")
    public String invalidatePersistedSessionAfterSuccessLogout(ProceedingJoinPoint proceedingJoinPoint){

        ResponseObject response =new ResponseObject();
        String jsonResponse="";
        try {
            //fetch parameters
            Object[] args = proceedingJoinPoint.getArgs();
            if (args[0] instanceof HttpServletRequest) {

                HttpServletRequest request = (HttpServletRequest) args[0];
                jsonResponse = validateSessionBeforeCallingRestApi(proceedingJoinPoint);
                sessionHandlerLogic.invalidatePersistedSession(request.getSession());

            }
        }catch (Throwable e){

            LOG.info("ERROR : "+e);
            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
            jsonResponse = AppUtils.convertToJson(response);

        }
        return jsonResponse;
    }
}
