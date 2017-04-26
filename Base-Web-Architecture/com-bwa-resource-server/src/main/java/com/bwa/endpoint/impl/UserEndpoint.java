package com.bwa.endpoint.impl;

import com.bwa.business.IUserLogic;
import com.bwa.endpoint.IUserEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Hassnain on 2/15/2017.
 */
@Component
public class UserEndpoint implements IUserEndpoint {

    @Autowired
    IUserLogic userLogicImpl;
    @Override
    public long getTotalUserCount() {
        return userLogicImpl.getTotalUserCount();
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userLogicImpl.deleteUserById(id);
    }
}
