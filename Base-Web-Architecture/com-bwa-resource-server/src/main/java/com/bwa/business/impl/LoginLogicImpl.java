package com.bwa.business.impl;

import com.bwa.business.ILoginLogic;
import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginLogicImpl implements ILoginLogic{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public boolean login(String userName, String credentials) {

        Customer customer = customerRepository.findByUserId(userName,"01");

        if(customer!=null) {
            Credential credential = credentialRepository.findByCustomerIdAndCredentialType(customer.getId(), 1);

            if(credential!=null){
                return credential.getCredential().equals(credentials);
            }
        }
        return false;
    }
}
