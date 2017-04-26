package com.bwa.business;

/**
 * Created by Hassnain on 08/02/2017.
 */
public interface IUserLogic {

    /**
     * return UserResponseBean by calling Dao which uses spring transaction template
     * @return
     *
    UserResponseBean getData();*/

    /**
     * returns total number of department by call the DepartmentRepository
     * @return
     */
    long getTotalUserCount();

    /**
     * return if the user is delete successfully
     * @param id
     * @return
     */
    boolean deleteUserById(Long id);

//    int getDeptCount();
}
