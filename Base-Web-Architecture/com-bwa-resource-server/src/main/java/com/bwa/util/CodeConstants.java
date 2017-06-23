package com.bwa.util;


public interface CodeConstants {

    String CODE_SUCCESS="00";
    String CODE_SUCCESS_MSG = "Success";
    int DEFAULT_EXCEPTION_CODE = 9999;
    String DEFAULT_EXCEPTION_MSG = "Unknown error occurred!";

    int ERROR_CODE_FIRST_NAME_REQUIRED=1;//Fist Name is required
    int ERROR_CODE_FIRST_NAME_INVALID=2;//''{0}''is invalid
    int ERROR_CODE_FIRST_NAME_INVALID_LENGTH=3;//First Name length should be between 1 to 40
    int ERROR_CODE_LAST_NAME_REQUIRED=4;//Last Name is required
    int ERROR_CODE_LAST_NAME_INVALID=5;//''{0}''is invalid
    int ERROR_CODE_LAST_NAME_INVALID_LENGTH=06;//Last Name length should be between 1 to 40
    int ERROR_CODE_EMAIL_ID_REQUIRED=7;//Email is required
    int ERROR_CODE_EMAIL_ID_INVALID=8;//''{0}''is invalid
    int ERROR_CODE_EMAIL_ID_INVALID_LENGTH=9;//Email length should be between 1 to 80
    int ERROR_CODE_MOBILE_NO_REQUIRED=10;//Mobile No. is required
    int ERROR_CODE_MOBILE_NO_INVALID=11;//''{0}''is invalid
    int ERROR_CODE_USER_NAME_REQUIRED=12;//User Name is required
    int ERROR_CODE_USER_NAME_INVALID=13;//''{0}''is invalid
    int ERROR_CODE_USER_NAME_INVALID_LENGTH=14;//User Name length should be between 3 to 15
    int ERROR_CODE_PASSWORD_REQUIRED=15;//Password is required
    int ERROR_CODE_PASSWORD_INVALID=16;//''{0}''is invalid
    int ERROR_CODE_PASSWORD_INVALID_LENGTH=17;//Password length should be between 8 to 15
    int ERROR_CODE_USER_EMAIL_ID_ALREADY_EXIST=18;//Account already exists with ''{0}'
    int ERROR_CODE_USER_NAME_ALREADY_EXIST=19;//User Name : ''{0 }''is not available
    int ERROR_CODE_MOBILE_NO_ALREADY_EXIST=20;//Mobile No : ''{0 }''is already associated with other user
    int ERROR_CODE_SIGN_UP_FAILED=21;//SignUp failed
    int ERROR_CODE_USER_NAME_DOES_NOT_EXIST=22;//User Name or password incorrect
    int ERROR_CODE_LOGIN_FAILED=23;//SignUp failed
    int ERROR_CODE_PASSWORD_CONFIRM_PASSWORD_MISMATCH=24;//Password and confirm password miss match
    int ERROR_CODE_ORGANIZATION_ID_REQUIRED =25;//Organization code is required
    int ERROR_CODE_ORGANIZATION_ID_INVALID =26;//Organization id ''{0]'' is invalid
    int ERROR_CODE_ROLE_ID_REQUIRED =27;//Role id is invalid
    int ERROR_CODE_CUSTOMER_ID_REQUIRED =28;//Customer id is required
    int ERROR_CODE_CUSTOMER_INVALID =29;//Customer id is invalid
    int ERROR_CODE_CUSTOMER_INACTIVE =30;//Customer is inactive
    int ERROR_CODE_CUSTOMER_ROLE_INVALID =31;//Role not associated with customer
    int ERROR_CODE_FETCHING_NAV_MENU_FAILED=32;//Failed to fetch navigation menus

    //Session related exceptions
    int ERROR_CODE_MAX_ALLOWANCE_SESSION_REACHED = 33;//Please logout from at least one device to login this device or wait for automatic logout
    int ERROR_CODE_SESSION_EXPIRED = 34;//Session timeout
    int ERROR_CODE_REQUIRED_LOGIN = 35;//Please Login


}
