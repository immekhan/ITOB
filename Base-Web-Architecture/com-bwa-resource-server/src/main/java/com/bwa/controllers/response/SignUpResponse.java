package com.bwa.controllers.response;

public class SignUpResponse {

    private Status status;

    public SignUpResponse(){

    }

    public SignUpResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
