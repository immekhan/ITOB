package com.bwa.business.handler;


import com.bwa.exceptions.SessionException;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

public interface ISessionHandlerLogic {

    void validatePersisted(HttpSession httpSession) throws SessionException, ParseException;
    void persistSession(HttpSession httpSession) throws SessionException;
    void invalidatePersistedSession(HttpSession httpSession);
    void updatePersistedSession(HttpSession httpSession);
}
