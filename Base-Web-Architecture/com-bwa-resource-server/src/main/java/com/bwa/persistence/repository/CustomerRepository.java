package com.bwa.persistence.repository;

import com.bwa.persistence.custom.repository.UserRepositoryCustom;
import com.bwa.persistence.model.Customer;

public interface CustomerRepository extends BaseRepository<Customer,Long> , UserRepositoryCustom {

    long count();
}
