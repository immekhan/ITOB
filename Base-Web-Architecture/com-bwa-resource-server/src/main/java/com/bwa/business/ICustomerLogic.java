package com.bwa.business;


public interface ICustomerLogic {

    /**
     * returns customer id after saving customer
     * @param customerTypeId
     * @param orgUnitId
     * @param userName
     * @param mobileNo
     * @param firstName
     * @param lastName
     * @param emailId
     * @param password
     * @return
     */
    Long saveCustomer(Long customerTypeId,String orgUnitId,String userName, String mobileNo, String firstName, String lastName, String emailId, String password);
}
