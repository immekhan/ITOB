package com.bwa.controllers;


import com.bwa.business.ICustomerLogic;
import com.bwa.persistence.model.Customer;
import com.bwa.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;

abstract class LoginControllerValidation {

    @Autowired
    private ICustomerLogic customerLogic;

    public String validateSignUpRequest( String firstName, String lastName,
                                       String emailId, String mobileNo,
                                       String userName, String password){

        if(firstName.isEmpty()){
            //is required
        }

        if( !firstName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)){
            //is invalid
        }

        if(firstName.length()<1 && firstName.length()>40){
            //first name should be between 1 to 40 characters
        }

        if(lastName.isEmpty()){
            //is required
        }

        if(!lastName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)){

        }

        if(lastName.length()<1 && lastName.length()>40){

        }

        if(emailId.isEmpty()){
            //is required
        }

        if(!emailId.matches(ControllerConstants.REGEX_EMAIL)){

        }

        if(mobileNo.isEmpty()){
            //is required
        }

        if(!mobileNo.matches(ControllerConstants.REGEX_NUMERIC)){

        }

        if(userName.isEmpty()){
            //is required
        }

        if (!userName.matches(ControllerConstants.REGEX_USERNAME)){

        }

        if (password.isEmpty()){
            //is required
        }

        if (password.length()<8 && password.length()>15){
            //password length should be between 8 to 15
        }

        //db level validations
        //fetch customer by user name
        //s customer = customerLogic.

        return "";
    }
}
