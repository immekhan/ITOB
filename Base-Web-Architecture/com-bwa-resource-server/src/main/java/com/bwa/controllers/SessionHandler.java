package com.bwa.controllers;

import com.bwa.business.handler.ISessionHandlerLogic;
import com.bwa.persistence.model.OrgUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionHandler {

    @Autowired private ISessionHandlerLogic sessionHandlerLogic;

    protected boolean validatePersistedSession(HttpSession httpSession){


        return false;
    }

    protected void persistSession(HttpSession httpSession){

    }

    protected void updatePersistedSession(HttpSession httpSession){

    }

    protected void deletePersistedSession(HttpSession httpSession){

    }


}
