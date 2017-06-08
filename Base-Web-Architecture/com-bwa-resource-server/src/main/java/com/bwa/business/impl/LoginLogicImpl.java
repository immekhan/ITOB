package com.bwa.business.impl;

import com.bwa.business.ICustomerLogic;
import com.bwa.business.ILoginLogic;
import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoginLogicImpl implements ILoginLogic{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    ICustomerLogic customerLogic;

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

    @Override //todo needs to return code for success
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
