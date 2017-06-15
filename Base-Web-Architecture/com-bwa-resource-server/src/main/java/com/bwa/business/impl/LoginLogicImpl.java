package com.bwa.business.impl;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.ILoginLogic;
import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.model.Menu;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.MenuRepository;
import com.bwa.persistence.repository.RolePrivilegeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LoginLogicImpl implements ILoginLogic{

    private static final Logger LOG = Logger.getLogger(LoginLogicImpl.class);

    @Autowired private CustomerRepository customerRepository;
    @Autowired private CredentialRepository credentialRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private RolePrivilegeRepository rolePrivilegeRepository;
    @Autowired private ICustomerLogic customerLogic;

    @Override
    public Customer login(String userName, String credentials) {

        Customer customer = customerRepository.findByUserId(userName,"01");

        if(customer!=null) {
            Credential credential = credentialRepository.findByCustomerIdAndCredentialType(customer.getId(), 1);

            if(credential!=null && credential.getCredential().equals(credentials)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public Long signUp(Long customerTypeId, String orgUnitId, String userName, String mobileNo, String firstName, String lastName, String emailId, String password) {

        Customer customer=customerLogic.saveCustomer( customerTypeId, orgUnitId, userName,
                mobileNo,  firstName,  lastName,
                emailId);

        if(customer!=null){
            customerLogic.saveCredentials( customer,  true,  password,  1,  1, new Date());
        }else{
            return 0l;
        }
        return customer.getId();
    }

}
