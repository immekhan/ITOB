package com.bwa.business.handler.impl;

import com.bwa.business.IUtilityLogic;
import com.bwa.business.handler.ISessionHandlerLogic;
import com.bwa.exceptions.SessionException;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Session;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.SessionRepository;
import com.bwa.util.AppUtils;
import com.bwa.util.CodeConstants;
import com.bwa.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class SessionHandlerLogicImpl implements ISessionHandlerLogic {

    private static final Logger LOG = Logger.getLogger(SessionHandlerLogicImpl.class);

    @Autowired private SessionRepository sessionRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private IUtilityLogic utilityLogic;

    @Override
    public boolean isPersistedSessionStillValid(HttpSession httpSession) {

        Optional<Session> sessionOpt=sessionRepository.findOne(httpSession.getId());

        Long customerId=(Long)httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID);
        String orgUnitId=(String) httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID);
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
    public void persistSession(HttpServletRequest request, Long customerId, String orgUnitId) throws SessionException{

        Customer customer = customerRepository.findById(customerId,orgUnitId);

        //validate max session allowed
        if(sessionRepository.findActiveSessionsByCustomerId(customerId).size()
                >=customer.getCustomerType().getSessionPolicy().getMaxSessionsAllowed()){
            throw new SessionException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MAX_ALLOWANCE_SESSION_REACHED,new Object[]{}));
        }
        //fetch date formatter for session date
        SimpleDateFormat dateFormat= AppUtils.getDateFormatter(Constants.DAT_PATTERN);
        Date dat_session_created = new Date();

        //set session attributes and interval
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID,customerId);
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID,orgUnitId);
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_CREATED,dateFormat.format(dat_session_created));
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_LAST_ACTIVITY,dateFormat.format(dat_session_created));
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ROLE_ID,customer.getCustomerType().getStrRole());
        httpSession.setMaxInactiveInterval(customer.getCustomerType().getSessionPolicy().getMaxSessionsAllowed());

        //create session object
        Session session=new Session(customerId);
        session.setId(httpSession.getId());
        session.setUserId(customer.getUserId());
        session.setDatLogon(dat_session_created);
        session.setDatLastActivity(dat_session_created);

        sessionRepository.save(session);
        LOG.info("Session successfully persisted..");
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
