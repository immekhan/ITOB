package com.bwa.business.impl;

import com.bwa.business.ICustomerLogic;
import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.CustomerTypeRepository;
import com.bwa.persistence.repository.OrgUnitRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerLogicImpl implements ICustomerLogic {

    private static final Logger LOG = Logger.getLogger(CustomerLogicImpl.class);

    @Autowired private CustomerRepository customerRepository;
    @Autowired private OrgUnitRepository orgUnitRepository;
    @Autowired private CustomerTypeRepository customerTypeRepository;
    @Autowired private CredentialRepository credentialRepository;

    @Override
    public Customer saveCustomer(Long customerTypeId,String orgUnitId,String userName,
                             String mobileNo, String firstName, String lastName,
                             String emailId) {

        Customer customer=new Customer();
        customer.setOrgUnit(orgUnitRepository.findOne(orgUnitId).get());
        customer.setCustomerType(customerTypeRepository.findOne(customerTypeId).get());
        customer.setDisplayName(firstName + " " + lastName);
        customer.setActive(true);
        customer.setBlacklistReason(0);
        customer.setDbTest(false);
        customer.setEmail(emailId.toLowerCase());
        customer.setUserId(userName.toLowerCase());
        customer.setMobileNo(mobileNo);
        customer.setCreator(0l);
        customer=customerRepository.save(customer);

        return customer;
    }

    @Override
    public void saveCredentials(Customer customer, boolean isActive, String password, int credentialType, int credentialStatus, Date datCreation) {

        Credential credential=new Credential();
        credential.setCustomer(customer);
        credential.setActive(true);
        credential.setCredential(password);
        credential.setCredentialType(credentialType);
        credential.setCredentialStatus(credentialStatus);
        credential.setCreationDate(new Date());
        credentialRepository.save(credential);
    }

    @Override
    public Customer fetchCustomerByUserId(String userId, String idOrgUnit) {
        //fetch the user by user id and orgUnit
        return customerRepository.findByUserId(userId,idOrgUnit);
    }

    @Override
    public Customer fetchCustomerByMobileNo(String mobile, String idOrgUnit) {
        //fetch the user by mobile and orgUnit
        return customerRepository.findByMobileNo(mobile,idOrgUnit);
    }

    @Override
    public Customer fetchCustomerByEmailId(String emailId, String idOrgUnit) {
        //fetch the user by email id and orgUnit
        return customerRepository.findByEmailId(emailId,idOrgUnit);
    }

    @Override
    public Customer fetchCustomerById(Long customerId, String idOrgUnit) {

        return customerRepository.findById(customerId,idOrgUnit);
    }
}
