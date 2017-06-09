package com.bwa.business;


import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Role;

import java.util.Date;

public interface ICustomerLogic {

    /**
     * @param customerTypeId
     * @param orgUnitId
     * @param userName
     * @param mobileNo
     * @param firstName
     * @param lastName
     * @param emailId
     * @return customer instance
     */
    Customer saveCustomer(Long customerTypeId, String orgUnitId
                        , String userName, String mobileNo
                        , String firstName, String lastName
                        , String emailId);

    /**
     * returns credential id after saving credentials associated with customer
     * @param customer
     * @param isActive
     * @param password
     * @param credentialType
     * @param credentialStatus
     * @param datCreation
     * @return credential id
     */
    void saveCredentials(Customer customer, boolean isActive
            , String password, int credentialType
            , int credentialStatus, Date datCreation);

    Customer fetchCustomerByUserId(String userId, String idOrgUnit);

    Customer fetchCustomerByMobileNo(String mobile, String idOrgUnit);

    Customer fetchCustomerByEmailId(String emailId, String idOrgUnit);

}
