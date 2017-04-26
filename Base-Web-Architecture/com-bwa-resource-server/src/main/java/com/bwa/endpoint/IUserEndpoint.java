package com.bwa.endpoint;

/**
 * Created by Hassnain on 2/15/2017.
 */
public interface IUserEndpoint {
    long getTotalUserCount();
    /**
     * return if the user is delete successfully
     * @param id
     * @return
     */
    boolean deleteUserById(Long id);
}
