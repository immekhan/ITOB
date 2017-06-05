package com.bwa.controllers;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.ILoginLogic;
import com.bwa.business.IUserLogic;
import com.bwa.util.AppUtils;
import com.bwa.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private ICustomerLogic customerLogic;

    @Autowired
    private ILoginLogic loginLogic;

    @CrossOrigin
    @RequestMapping(value = "/signUp", method = { RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public String singUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("emailId") String emailId,
                         @RequestParam("mobileNo") String mobileNo,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password
                         ) {
        try {

            Long customerId=customerLogic.signUp(1l,"01",userName,mobileNo,firstName,lastName,emailId,password);
            if(customerId==null){
                //failure page return
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //succcess page return
        return AppUtils.convertToJson("Success");
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = { RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
    @ResponseBody
    public String login( @RequestParam("userName") String userName,
                         @RequestParam("password") String password) {

        boolean isLoginVerified =false;
        try {
            isLoginVerified=loginLogic.login(userName.toLowerCase(),password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOG.info("Login Success : " , isLoginVerified);
        //succcess page return
        return AppUtils.convertToJson(isLoginVerified?"Login Success":"Login Failed");
    }
}
