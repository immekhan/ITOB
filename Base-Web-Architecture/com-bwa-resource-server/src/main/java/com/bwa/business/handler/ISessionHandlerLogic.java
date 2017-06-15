package com.bwa.business.handler;


import javax.servlet.http.HttpSession;

public interface ISessionHandlerLogic {

    boolean isPersistedSessionStillValid(HttpSession httpSession);
    void persistSession(HttpSession httpSession);
    void deletePersistedSession(HttpSession httpSession);
    void updatePersistedSession(HttpSession httpSession);
}
