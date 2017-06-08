package com.bwa.util;


public interface ControllerConstants {

    String REGEX_NUMERIC = "^[0-9]+$";
    String REGEX_ALPHANUMERIC = "^$|^[A-Za-z0-9' ']*$";
    String REGEX_FIRST_LAST_NAME = "[a-zA-Z]{3,40}";
    String REGEX_USERNAME = "^[a-z0-9_-]{3,15}$";
    String REGEX_EMAIL = "^([a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]){0,1}(,[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z])*$";


}
