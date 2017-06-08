package com.bwa.controllers.response;

public class LoginResponse {

    private Status status;

    public LoginResponse(){

    }

    public LoginResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
