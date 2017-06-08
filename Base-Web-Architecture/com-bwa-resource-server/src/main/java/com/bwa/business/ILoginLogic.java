package com.bwa.business;

public interface ILoginLogic {

    /**
     * @param userName
     * @param credentials
     * @return true on successful verification
     */
    boolean login(String userName,String credentials);

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
