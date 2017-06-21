package com.bwa.controllers;

import com.bwa.business.handler.ISessionHandlerLogic;
import com.bwa.exceptions.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionHandler {

    @Autowired private ISessionHandlerLogic sessionHandlerLogic;

    protected boolean validatePersistedSession(HttpServletRequest request){


        return false;
    }

    protected void persistSession(HttpServletRequest request, Long customerId, String orgUnitId) throws SessionException{
        sessionHandlerLogic.persistSession(request,customerId,orgUnitId);
    }

    protected void updatePersistedSession(HttpServletRequest request){

    }

    protected void deletePersistedSession(HttpServletRequest request){

    }


}
