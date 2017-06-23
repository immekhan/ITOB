package com.bwa.business.handler;


import com.bwa.exceptions.SessionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ISessionHandlerLogic {

    void validatePersisted(HttpSession httpSession) throws SessionException;
    void persistSession(HttpSession httpSession) throws SessionException;
    void invalidatePersistedSession(HttpSession httpSession);
    void updatePersistedSession(HttpSession httpSession);
}
