package com.bwa.exceptions;

public class NavigationMenuException extends CustomException{

    public NavigationMenuException(Throwable cause) {
        super(cause);
    }

    public NavigationMenuException(String message) {
        super(message);
    }

    public NavigationMenuException(String message, Throwable cause) {
        super( message, cause);
    }

}
