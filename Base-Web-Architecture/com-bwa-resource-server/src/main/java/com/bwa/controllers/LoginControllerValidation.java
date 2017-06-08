package com.bwa.controllers;


import com.bwa.business.ICustomerLogic;
import com.bwa.business.IUtilityLogic;
import com.bwa.exceptions.CustomException;
import com.bwa.exceptions.LoginException;
import com.bwa.exceptions.SignUpException;
import com.bwa.util.CodeConstants;
import com.bwa.util.ControllerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class LoginControllerValidation {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoginControllerValidation.class);
    @Autowired
    private ICustomerLogic customerLogic;

    @Autowired
    private IUtilityLogic utilityLogic;
    //todo aop work
    public String validateSignUpRequest( String firstName, String lastName,
                                         String emailId, String mobileNo,
                                         String userName, String password) throws SignUpException{

        if (firstName==null || firstName.isEmpty()) {
//            LOG.info("Fist Name is required");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_REQUIRED,new Object[]{}));
        }

        if (!firstName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)) {
//            LOG.info(firstName + "is invalid");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_INVALID,new Object[]{firstName}));
        }

        if (firstName.length() < 1 || firstName.length() > 40) {
//            LOG.info("First Name length should be between 1 to 40");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_INVALID_LENGTH,new Object[]{}));

        }

        if (lastName==null || lastName.isEmpty()) {
//            LOG.info("Last Name is required");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_REQUIRED,new Object[]{}));

        }

        if (!lastName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)) {
//            LOG.info(lastName + "is invalid");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_INVALID,new Object[]{lastName}));

        }

        if (lastName.length() < 1 || lastName.length() > 40) {
//            LOG.info("Last Name length should be between 1 to 40");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_INVALID_LENGTH,new Object[]{}));

        }

        if (emailId==null || emailId.isEmpty()) {
//            LOG.info("Email is required");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_REQUIRED,new Object[]{}));
        }

        if (!emailId.matches(ControllerConstants.REGEX_EMAIL)) {
//            LOG.info(emailId+ " is invalid");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_INVALID,new Object[]{emailId}));
        }

        if (emailId.length() < 1 || emailId.length() > 80) {
//            LOG.info("Email length should be between 1 to 80");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_INVALID_LENGTH,new Object[]{}));

        }

        if (mobileNo==null || mobileNo.isEmpty()) {
//            LOG.info("Mobile No. is required");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_REQUIRED,new Object[]{}));
        }

        if (!mobileNo.matches(ControllerConstants.REGEX_NUMERIC)) {
//            LOG.info(mobileNo+" is invalid");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_INVALID,new Object[]{mobileNo}));
        }

        try {
            //validate user name and password
            validateUserNameAndPassword( userName, password);

        } catch (CustomException e) {
            throw new SignUpException(e.getMessage());
        }


        //db level validations
        if (customerLogic.fetchCustomerByEmailId(emailId, "01") != null) {
//            LOG.info("User Name : '"+userName+"' is not available");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_EMAIL_ID_ALREADY_EXIST,new Object[]{emailId}));
        }

        if (customerLogic.fetchCustomerByUserId(userName, "01") != null) {
//            LOG.info("User Name : '"+userName+"' is not available");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_ALREADY_EXIST,new Object[]{userName}));
        }

        if (customerLogic.fetchCustomerByMobileNo(mobileNo, "01") != null) {
//            LOG.info("Mobile No : '"+mobileNo+"' is already associated with other user");
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_ALREADY_EXIST,new Object[]{mobileNo}));
        }

        return CodeConstants.CODE_SUCCESS;
    }

    public String validateLoginRequest( String userName, String password) throws LoginException{

        try {
            //validate user name and password
            validateUserNameAndPassword( userName, password);

        } catch (CustomException e) {
            throw new LoginException(e.getMessage());
        }

        //db level validations
        if (customerLogic.fetchCustomerByUserId(userName, "01") == null) {
            throw new LoginException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_ALREADY_EXIST,new Object[]{}));
        }

        return  CodeConstants.CODE_SUCCESS;
    }

    private void validateUserNameAndPassword( String userName, String password) throws CustomException{

        if (userName==null ||userName.isEmpty()) {
//            LOG.info("User Name is required");
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_REQUIRED,new Object[]{}));
        }

        if (userName.length() < 3 || userName.length() > 15) {
//            LOG.info(userName +" is invalid");
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_INVALID_LENGTH,new Object[]{}));
        }

        if (!userName.matches(ControllerConstants.REGEX_USERNAME)) {
//            LOG.info("User Name length should be between 3 to 15");
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_INVALID,new Object[]{}));
        }

        if (password==null || password.isEmpty()) {
//            LOG.info("Password is required");
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_PASSWORD_REQUIRED,new Object[]{}));
        }

        if (password.length() < 8 || password.length() > 15) {
//            LOG.info("Password length should be between 8 to 15");
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_PASSWORD_INVALID_LENGTH,new Object[]{}));
        }
    }

}
