package com.bwa.business;

public interface ILoginLogic {

    /**
     * @param userName
     * @param credentials
     * @return true on successful verification
     */
    boolean login(String userName,String credentials);
}
