package com.bwa.business.impl;

import com.bwa.business.IUserLogic;
import com.bwa.persistence.model.UserMBean;
import com.bwa.persistence.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Hassnain on 09/02/2017.
 */
@Component
public class UserLogicImpl implements IUserLogic {

    private static final Logger LOG=Logger.getLogger(UserLogicImpl.class);

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
        LOG.info("userRepository.count() : " + userRepository.count());
        return userRepository.count();
    }

    @Transactional
    @Override
    public boolean deleteUserById(Long id){
        Optional<UserMBean> userMBean=userRepository.findOne(id);
        userRepository.delete(userMBean.get());
        return true;
    }

}
