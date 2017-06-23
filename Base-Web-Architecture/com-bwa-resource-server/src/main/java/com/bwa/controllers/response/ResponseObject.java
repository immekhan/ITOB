package com.bwa.controllers.response;

import com.bwa.controllers.response.bean.Status;

public class ResponseObject {

    private Status status;
    private Object data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
