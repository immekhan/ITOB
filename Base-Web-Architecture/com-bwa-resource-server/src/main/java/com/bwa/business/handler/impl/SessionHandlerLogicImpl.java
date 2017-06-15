package com.bwa.business.handler.impl;

import com.bwa.business.handler.ISessionHandlerLogic;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Session;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.SessionRepository;
import com.bwa.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class SessionHandlerLogicImpl implements ISessionHandlerLogic {

    @Autowired private SessionRepository sessionRepository;
    @Autowired private CustomerRepository customerRepository;

    @Override
    public boolean isPersistedSessionStillValid(HttpSession httpSession) {

        Optional<Session> sessionOpt=sessionRepository.findOne(httpSession.getId());

        Long customerId=(Long)httpSession.getAttribute(Constant.SESSION_KEY_CUSTOMER_ID);
        String orgUnitId=(String) httpSession.getAttribute(Constant.SESSION_KEY_ORG_UNIT_ID);
        long lastAccessedTimeInMilliSeconds=httpSession.getLastAccessedTime() - httpSession.getCreationTime();
        if(sessionOpt.isPresent()){
            //fetch orgUnit id from httpSession
            Session session=sessionOpt.get();
            Customer customer = customerRepository.findById(session.getCustomerId(),orgUnitId);
            long sessionPolicyMaxTimeAllowedInMilliSecond=customer.getCustomerType().getSessionPolicy().getSessionTimeoutSeconds()*1000;
            if (lastAccessedTimeInMilliSeconds > sessionPolicyMaxTimeAllowedInMilliSecond){
                return false;
            }
        }
        return true;
    }

    @Override
    public void persistSession(HttpSession httpSession) {
        //create session object

        //business rules
        //1. check max allowed session
        //2.
        //3.

        //set session id
        //set customer id
        //set user id
        //set datLogon
        //set datLastAccessed
        //set datCreated etc

    }

    @Override
    public void deletePersistedSession(HttpSession httpSession) {
        //delete by id
    }

    @Override
    public void updatePersistedSession(HttpSession httpSession) {
        //update session last activity by session id
    }
}
