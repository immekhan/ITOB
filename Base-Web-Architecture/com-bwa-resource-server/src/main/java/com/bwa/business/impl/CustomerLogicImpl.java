package com.bwa.business.impl;

import com.bwa.business.ICustomerLogic;
import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.CustomerTypeRepository;
import com.bwa.persistence.repository.OrgUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerLogicImpl implements ICustomerLogic {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrgUnitRepository orgUnitRepository;
    @Autowired
    CustomerTypeRepository customerTypeRepository;
    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public Long saveCustomer(Long customerTypeId,String orgUnitId,String userName,
                             String mobileNo, String firstName, String lastName,
                             String emailId, String password) {

        Customer customer=new Customer();
        customer.setOrgUnit(orgUnitRepository.findOne(orgUnitId).get());
        customer.setCustomerType(customerTypeRepository.findOne(customerTypeId).get());
        customer.setDisplayName(firstName+" "+lastName);
        customer.setActive(true);
        customer.setBlacklistReason(0);
        customer.setDbTest(false);
        customer.setEmail(emailId.toLowerCase());
        customer.setUserId(userName.toLowerCase());
        customer.setMobileNo(mobileNo);
        customer.setCreator(0l);
        customer=customerRepository.save(customer);

        Credential credential=new Credential();
        credential.setCustomer(customer);
        credential.setActive(true);
        credential.setCredential(password);
        credential.setCredentialType(1);
        credential.setCredentialStatus(1);
        credential.setCreationDate(new Date());
        credentialRepository.save(credential);


        return customer.getId();
    }
}
