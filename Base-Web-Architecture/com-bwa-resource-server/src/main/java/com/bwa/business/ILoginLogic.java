package com.bwa.business;

import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Menu;

import java.util.List;

public interface ILoginLogic {

    /**
     * @param userName
     * @param credentials
     * @return true on successful verification
     */
    Customer login(String userName, String credentials);

    /**
     * @param customerTypeId
     * @param orgUnitId
     * @param userName
     * @param mobileNo
     * @param firstName
     * @param lastName
     * @param emailId
     * @param password
     * @return customer id
     */
    Long signUp(Long customerTypeId, String orgUnitId
            , String userName, String mobileNo
            , String firstName, String lastName
            , String emailId, String password);

}
