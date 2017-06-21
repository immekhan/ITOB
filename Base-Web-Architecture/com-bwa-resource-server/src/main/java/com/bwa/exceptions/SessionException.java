package com.bwa.exceptions;

public class SessionException extends CustomException{

    public SessionException(Throwable cause) {
        super(cause);
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super( message, cause);
    }

}
