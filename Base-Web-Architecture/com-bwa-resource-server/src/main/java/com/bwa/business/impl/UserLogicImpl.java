package com.bwa.business.impl;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.IUserLogic;
import com.bwa.persistence.model.UserMBean;
import com.bwa.persistence.repository.RolePrivilegeRepository;
import com.bwa.persistence.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserLogicImpl implements IUserLogic {
    /**
     * This is used for logging.
     */
    private static final Logger LOG = Logger.getLogger(UserLogicImpl.class);

    @Autowired private UserRepository userRepository;
    @Autowired private RolePrivilegeRepository rolePrivilegeRepository;
    @Autowired private ICustomerLogic customerLogic;

    @Transactional
    @Override
    public final long getTotalUserCount() {
        UserMBean userMBean = new UserMBean();
        userMBean.setAddressId("00001");
        userMBean.setPhoneNo("090078601");
        userMBean.setStrName("Hello World");
        userRepository.save(userMBean);
        LOG.info("userRepository.count() : " + userRepository.count());

        // this is just to test spring jdbc template and custom repository
       /* List<String> privilegeList= privilegeList();
        for(String priv: privilegeList){
            LOG.info("Privilege  : "+priv);
        }*/
        /*Long customerId=customerLogic.saveCustomer(1l,"01",
                "userName","03212414235",
                "first Name","last Name","abc@abc.com","password");*/
        return userRepository.count();
    }

    @Transactional
    @Override
    public final boolean deleteUserById(final Long id) {
        Optional<UserMBean> userMBean = userRepository.findOne(id);
        userRepository.delete(userMBean.get());
        return true;
    }

    public List<String> privilegeList(){
        return rolePrivilegeRepository.findPrivilegeByRole("ROLE_TEST");
    }

}
