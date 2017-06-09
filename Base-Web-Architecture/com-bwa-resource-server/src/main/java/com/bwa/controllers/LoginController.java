package com.bwa.controllers;

import com.bwa.business.ILoginLogic;
import com.bwa.controllers.response.LoginResponse;
import com.bwa.controllers.response.SignUpResponse;
import com.bwa.controllers.response.Status;
import com.bwa.exceptions.CustomException;
import com.bwa.exceptions.LoginException;
import com.bwa.exceptions.SignUpException;
import com.bwa.persistence.model.Customer;
import com.bwa.util.AppUtils;
import com.bwa.util.CodeConstants;
import com.bwa.util.Constant;
import com.bwa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private ILoginLogic loginLogic;

    @Autowired
    private LoginControllerValidation loginControllerValidation;

    @CrossOrigin
    @RequestMapping(value = "/signUp", method = { RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public String singUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("emailId") String emailId,
                         @RequestParam("mobileNo") String mobileNo,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("rpassword") String rTpassword,
                         @RequestParam("idOrgUnit") String idOrgUnit) {

        SignUpResponse response=new SignUpResponse();
        String validationResponse="";
        try {

            validationResponse=loginControllerValidation.validateSignUpRequest(firstName,lastName,emailId
                    ,mobileNo,userName,password,rTpassword,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Long customerId = loginLogic.signUp(1l, "01", userName,
                        mobileNo, firstName, lastName, emailId, password);

                Status status=new Status();
                if (customerId != null) {
                    status.setCode(CodeConstants.CODE_SUCCESS);
                    status.setMsg(CodeConstants.CODE_SUCCESS_MSG);
                }else{
                    status.setCode(Integer.toString(CodeConstants.ERROR_CODE_SIGN_UP_FAILED));
                }
                response.setStatus(status);
            }

        }catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_SIGN_UP_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }

        return AppUtils.convertToJson(response);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = { RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public String login( @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("idOrgUnit") String idOrgUnit) {

        LoginResponse response=new LoginResponse();
        String validationResponse =null;
        try {
            validationResponse=loginControllerValidation.validateLoginRequest(userName,password,idOrgUnit);

            if(validationResponse.equals(CodeConstants.CODE_SUCCESS)) {

                Customer customer=loginLogic.login(userName.toLowerCase(),password);

                Status status=new Status();
                if (customer!=null) {

                    status.setCode(CodeConstants.CODE_SUCCESS);
                    status.setMsg(CodeConstants.CODE_SUCCESS_MSG);
                    response.setRole(customer.getCustomerType().getStrRole());
                    response.setCustomerId(customer.getId());
                }else{
                    status.setCode(Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED));
                }
                response.setStatus(status);
            }

        } catch (CustomException e) {

            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.ERROR_CODE_LOGIN_FAILED),e.getMessage()));
        } catch (Exception e){
            LOG.info("Error : "+e.getMessage());

            response.setStatus(ControllerUtils.convertToStatus(
                    Integer.toString(CodeConstants.DEFAULT_EXCEPTION_CODE),CodeConstants.DEFAULT_EXCEPTION_MSG));
        }

        return AppUtils.convertToJson(response);
    }

}
