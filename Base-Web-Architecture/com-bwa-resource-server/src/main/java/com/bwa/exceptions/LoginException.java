package com.bwa.exceptions;

public class LoginException extends CustomException{

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super( message, cause);
    }
}
