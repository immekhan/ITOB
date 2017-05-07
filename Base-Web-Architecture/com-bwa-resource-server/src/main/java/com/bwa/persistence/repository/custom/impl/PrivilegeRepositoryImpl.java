package com.bwa.persistence.repository.custom.impl;

import com.bwa.persistence.model.Privilege;
import com.bwa.persistence.repository.custom.PrivilegeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public class PrivilegeRepositoryImpl implements PrivilegeRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Privilege> findByRole(String role) {
        return null;
    }
    /*@Override
    public List<Privilege> findByRole(String role) {
        String hql=new StringBuilder().append("SELECT ID_PRIVILEGE FROM ITOB_ROLE_PRIVILEGES")
                .append("")
        return null;
    }*/
}
