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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void validatePersisted(HttpSession httpSession) throws SessionException {

        Optional<Session> sessionOpt=sessionRepository.findOne(httpSession.getId());

        Long customerId=(Long)httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID);
        String idOrgUnit=(String) httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID);
        Date dat_last_activity=(Date) httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_LAST_ACTIVITY);

        Date dat_current_date=new Date();

        if(sessionOpt.isPresent()){

            Session session=sessionOpt.get();
            if(session.getDatLogOff()!=null){
                LOG.info("*****Expiring Session*****");
                httpSession.invalidate();
                throw new SessionException(utilityLogic
                        .fetchExceptionMsg(CodeConstants.ERROR_CODE_SESSION_EXPIRED,new Object[]{}));
            }

            if(session.getCustomerId().longValue()!=customerId){
                LOG.info("*****Expiring Session*****");
                invalidatePersistedSession(httpSession);
                throw new SessionException(utilityLogic
                        .fetchExceptionMsg(CodeConstants.ERROR_CODE_SESSION_EXPIRED,new Object[]{}));
            }

            long lastAccessedTimeInMilliSeconds=(dat_current_date.getTime() - dat_last_activity.getTime());

            //fetch customer to fetch the session policy
            Customer customer = customerRepository.findById(session.getCustomerId(),idOrgUnit);
            //fetch the policy
            long sessionPolicyMaxTimeAllowedInMilliSecond=customer.getCustomerType().getSessionPolicy()
                    .getSessionTimeoutSeconds()*1000;

            if (lastAccessedTimeInMilliSeconds > sessionPolicyMaxTimeAllowedInMilliSecond){

                LOG.info("*****Expiring Session*****");
                invalidatePersistedSession(httpSession);

                throw new SessionException(utilityLogic
                        .fetchExceptionMsg(CodeConstants.ERROR_CODE_SESSION_EXPIRED,new Object[]{}));
            }

            LOG.info("*****Updating Session*****");
            updatePersistedSession(httpSession);

        }else{
            LOG.info("*****Error*****");
            LOG.info("Session ID Invalid : "+httpSession.getId().toString());
            throw new SessionException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_REQUIRED_LOGIN,new Object[]{}));
        }
    }

    @Transactional
    @Override
    public void persistSession(HttpSession httpSession) throws SessionException{

        Long customerId=(Long)httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_CUSTOMER_ID);
        String idOrgUnit=(String) httpSession.getAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID);
        Customer customer = customerRepository.findById(customerId,idOrgUnit);

        //validate max session allowed
        if(sessionRepository.findActiveSessionsByCustomerId(customerId).size()
                >=customer.getCustomerType().getSessionPolicy().getMaxSessionsAllowed()){
            LOG.info("****Cannot login due to logged In to max no of devices****");
            throw new SessionException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_MAX_ALLOWANCE_SESSION_REACHED,new Object[]{}));
        }
        //fetch date formatter for session date
        SimpleDateFormat dateFormat= AppUtils.getDateFormatter(Constants.DAT_PATTERN);
        Date dat_session_created = new Date();

        //set session attributes and interval
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_CREATED,dateFormat.format(dat_session_created));
        httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_LAST_ACTIVITY,dateFormat.format(dat_session_created));
        httpSession.setMaxInactiveInterval(customer.getCustomerType().getSessionPolicy().getMaxSessionsAllowed());

        //create session object
        Session session=new Session(customerId);
        session.setCustomerId(customerId);
        session.setId(httpSession.getId());
        session.setUserId(customer.getUserId());
        session.setDatLogon(dat_session_created);
        session.setDatLastActivity(dat_session_created);

        sessionRepository.save(session);
        LOG.info("****Session successfully persisted****");
    }

    @Transactional
    @Override
    public void invalidatePersistedSession(HttpSession httpSession) {
        //mark session inactive delete by id
        Optional<Session> optSession=sessionRepository.findOne(httpSession.getId());
        if(optSession.isPresent()){

            //fetch date formatter for session date
            Date dat_session_logout = new Date();

            Session session=optSession.get();
            session.setDatLastActivity(dat_session_logout);
            session.setDatLogOff(dat_session_logout);
            sessionRepository.save(session);
        }
        httpSession.invalidate();
        LOG.info("****Session invalidated successfully****");
    }

    @Transactional
    @Override
    public void updatePersistedSession(HttpSession httpSession) {
        //update session last activity by session id
        Optional<Session> optSession=sessionRepository.findOne(httpSession.getId());
        if(optSession.isPresent()){

            //fetch date formatter for session date
            SimpleDateFormat dateFormat= AppUtils.getDateFormatter(Constants.DAT_PATTERN);
            Date dat_session_lastActivity = new Date();

            Session session=optSession.get();
            session.setDatLastActivity(dat_session_lastActivity);
            sessionRepository.save(session);
            httpSession.setAttribute(Constants.SESSION_ATTRIBUTE_KEY_DAT_LAST_ACTIVITY,dateFormat.format(dat_session_lastActivity));

        }
    }

    //todo fetch all the active sessions with logout date null
    //todo mark these session logout according to session policy
    //todo check why every time a new session is being created whenever request submitted from post man
    //todo once logged in util session expire or user logout when every the use hits url login redirect to dash board
}
