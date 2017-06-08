package com.bwa.exceptions;

public class SignUpException extends CustomException{

    public SignUpException(Throwable cause) {
        super(cause);
    }

    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(String message, Throwable cause) {
        super( message, cause);
    }

}
