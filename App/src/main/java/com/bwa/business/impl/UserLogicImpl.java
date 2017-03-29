package com.bwa.business.impl;

import com.bwa.business.IUserLogic;
import com.bwa.persistence.model.UserMBean;
import com.bwa.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Hassnain on 09/02/2017.
 */
@Component
public class UserLogicImpl implements IUserLogic {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public long getTotalUserCount() {
        UserMBean userMBean = new UserMBean();
        userMBean.setAddressId("00001");
        userMBean.setPhoneNo("090078601");
        userMBean.setStrName("Hello World");
        userRepository.save(userMBean);

        return userRepository.count();
    }

}
