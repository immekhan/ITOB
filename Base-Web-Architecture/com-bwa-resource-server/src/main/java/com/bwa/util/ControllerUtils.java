package com.bwa.util;

import com.bwa.controllers.response.Status;

public class ControllerUtils {

    public static Status convertToStatus(String code, String msg){
        return new Status(code,msg);
    }
}
