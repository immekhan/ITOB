package com.bwa.persistence.repository.custom.impl;

import com.bwa.persistence.repository.custom.CustomerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;
}
