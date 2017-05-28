package com.bwa.controllers;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.IUserLogic;
import com.bwa.util.AppUtils;
import com.bwa.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ICustomerLogic customerLogic;

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
}
