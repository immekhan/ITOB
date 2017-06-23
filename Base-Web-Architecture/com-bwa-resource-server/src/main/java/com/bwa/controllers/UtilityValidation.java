package com.bwa.controllers;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.IUtilityLogic;
import com.bwa.persistence.model.Customer;
import com.bwa.util.CodeConstants;
import com.bwa.util.ControllerConstants;
import org.apache.log4j.Logger;
import org.hibernate.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilityValidation {

    private static final Logger LOG = Logger.getLogger(UtilityValidation.class);

    @Autowired private IUtilityLogic utilityLogic;
    @Autowired private ICustomerLogic customerLogic;

    public boolean isValidOrgUnitId(String idOrgUnit){
        return utilityLogic.fetchOrgUnit(idOrgUnit) !=null;
    }


    public void validateOrgUnitId(String idOrgUnit) throws CacheException{

        LOG.info("");
        if (idOrgUnit==null || idOrgUnit.isEmpty()) {
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_ORGANIZATION_ID_REQUIRED,new Object[]{}));
        }

        if (!idOrgUnit.matches(ControllerConstants.REGEX_ALPHANUMERIC)) {
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_ORGANIZATION_ID_INVALID,new Object[]{idOrgUnit}));
        }

        if (!isValidOrgUnitId(idOrgUnit)) {
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_ORGANIZATION_ID_INVALID,new Object[]{idOrgUnit}));
        }
    }

    public Customer validateCustomer(Long customerId, String idOrgUnit) throws CacheException{

        if(customerId==null){
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_CUSTOMER_ID_REQUIRED,new Object[]{}));
        }

        Customer customer=customerLogic.fetchCustomerById(customerId,idOrgUnit);
        if(customer==null){
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_CUSTOMER_INVALID,new Object[]{}));
        }

        if(!customer.isActive()){
            throw new CacheException(utilityLogic
                    .fetchExceptionMsg(CodeConstants.ERROR_CODE_CUSTOMER_INACTIVE,new Object[]{}));
        }
        return customer;
    }
}
