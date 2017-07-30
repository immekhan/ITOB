package com.bwa.business.impl;

import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.CustomerRepository;
import com.bwa.persistence.repository.RolePrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AuthenticationServiceImpl implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Autowired
    private CredentialRepository credentialRepository;
    @Override
    public UserDetails loadUserByUsername(String userData)
            throws UsernameNotFoundException, DataAccessException {

        String data[]=userData.split(":");
        String userId=data[0].trim();
        String orgUnitId=data[1].trim();

        Customer customer = customerRepository.findByUserId(userId,orgUnitId);
        if(customer == null){
            throw new UsernameNotFoundException("UserName "+userId+" not found");
        }
        return new CustomUserDetailsImpl(customer , rolePrivilegeRepository , credentialRepository);
    }
}
