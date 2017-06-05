package com.bwa.persistence.repository;

import com.bwa.persistence.model.Credential;
import com.bwa.persistence.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface CredentialRepository extends BaseRepository<Credential,Long> {

    @Query("select c from Credential c where c.customer.id = :id and c.credentialType = :credentialType")
    Credential findByCustomerIdAndCredentialType(@Param("id") Long id,
                      @Param("credentialType") Integer credentialType);
}
