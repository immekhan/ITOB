package com.bwa.controllers;


import com.bwa.business.ICustomerLogic;
import com.bwa.business.IUtilityLogic;
import com.bwa.exceptions.CustomException;
import com.bwa.exceptions.LoginException;
import com.bwa.exceptions.SignUpException;
import com.bwa.util.CodeConstants;
import com.bwa.util.Constant;
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

    @Autowired
    private UtilityValidation utilityValidation;

    public String validateSignUpRequest( String firstName, String lastName,
                                         String emailId, String mobileNo,
                                         String userName, String password,
                                         String rTpassword , String idOrgUnit) throws CustomException {


        if (firstName==null || firstName.isEmpty()) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_REQUIRED,new Object[]{}));
        }

        if (!firstName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_INVALID,new Object[]{firstName}));
        }

        if (firstName.length() < Constant.FIRST_LAST_NAME_LENGTH_MIN || firstName.length() > Constant.FIRST_LAST_NAME_LENGTH_MAX) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_FIRST_NAME_INVALID_LENGTH,new Object[]{}));

        }

        if (lastName==null || lastName.isEmpty()) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_REQUIRED,new Object[]{}));

        }

        if (!lastName.matches(ControllerConstants.REGEX_FIRST_LAST_NAME)) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_INVALID,new Object[]{lastName}));

        }

        if (lastName.length() < Constant.FIRST_LAST_NAME_LENGTH_MIN || lastName.length() > Constant.FIRST_LAST_NAME_LENGTH_MAX) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_LAST_NAME_INVALID_LENGTH,new Object[]{}));

        }

        if (emailId==null || emailId.isEmpty()) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_REQUIRED,new Object[]{}));
        }

        if (!emailId.matches(ControllerConstants.REGEX_EMAIL)) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_INVALID,new Object[]{emailId}));
        }

        if (emailId.length() < Constant.EMAIL_ID_LENGTH_MIN || emailId.length() > Constant.EMAIL_ID_LENGTH_MAX) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_EMAIL_ID_INVALID_LENGTH,new Object[]{}));

        }

        if (mobileNo==null || mobileNo.isEmpty()) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_REQUIRED,new Object[]{}));
        }

        if (!mobileNo.matches(ControllerConstants.REGEX_NUMERIC)) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_INVALID,new Object[]{mobileNo}));
        }

        if (!password.equals(rTpassword)) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_PASSWORD_CONFIRM_PASSWORD_MISMATCH,new Object[]{mobileNo}));
        }

        //validate user name and password
        validateUserNameAndPassword( userName, password,idOrgUnit);

        //db level validations
        if (customerLogic.fetchCustomerByEmailId(emailId, idOrgUnit) != null) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_EMAIL_ID_ALREADY_EXIST,new Object[]{emailId}));
        }

        if (customerLogic.fetchCustomerByUserId(userName, idOrgUnit) != null) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_ALREADY_EXIST,new Object[]{userName}));
        }

        if (customerLogic.fetchCustomerByMobileNo(mobileNo, idOrgUnit) != null) {
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MOBILE_NO_ALREADY_EXIST,new Object[]{mobileNo}));
        }

        return CodeConstants.CODE_SUCCESS;
    }

    public String validateLoginRequest( String userName, String password , String idOrgUnit) throws CustomException{

        //validate user name and password
        validateUserNameAndPassword( userName, password,idOrgUnit);

        //db level validations
        if (customerLogic.fetchCustomerByUserId(userName, idOrgUnit) == null) {
            throw new LoginException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_DOES_NOT_EXIST,new Object[]{}));
        }

        return  CodeConstants.CODE_SUCCESS;
    }

    private void validateUserNameAndPassword( String userName, String password,String idOrgUnit) throws CustomException{

        utilityValidation.validateOrgUnitId(idOrgUnit);

        if (userName==null ||userName.isEmpty()) {
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_REQUIRED,new Object[]{}));
        }

        if (userName.length() < Constant.USER_NAME_LENGTH_MIN || userName.length() > Constant.PASSWORD_LENGTH_MAX) {
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_INVALID_LENGTH,new Object[]{}));
        }

        if (!userName.matches(ControllerConstants.REGEX_USERNAME)) {
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_USER_NAME_INVALID,new Object[]{}));
        }

        if (password==null || password.isEmpty()) {
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_PASSWORD_REQUIRED,new Object[]{}));
        }

        if (password.length() < Constant.PASSWORD_LENGTH_MIN || password.length() > Constant.PASSWORD_LENGTH_MAX) {
            throw new CustomException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_PASSWORD_INVALID_LENGTH,new Object[]{}));
        }
    }

    public String validateFetchNavMenuRequest( String idRole, Long customerId,String idOrgUnit) throws CustomException{

        utilityValidation.validateOrgUnitId(idOrgUnit);

        if(idRole ==null || idRole.isEmpty()){
            throw new SignUpException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_ROLE_ID_REQUIRED,new Object[]{}));
        }

        utilityValidation.validateCustomer(customerId,idOrgUnit);
//todo validate customer type id to request role id
        return  CodeConstants.CODE_SUCCESS;
    }

}
