package com.bwa.business.handler;


import com.bwa.exceptions.SessionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ISessionHandlerLogic {

    boolean isPersistedSessionStillValid(HttpSession httpSession);
    void persistSession(HttpServletRequest request, Long customerId, String orgUnitId) throws SessionException;
    void deletePersistedSession(HttpSession httpSession);
    void updatePersistedSession(HttpSession httpSession);
}
