package com.bwa.persistence.repository.custom.impl;

import com.bwa.persistence.repository.custom.RolePrivilegeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public class RolePrivilegeRepositoryImpl implements RolePrivilegeRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> findPrivilegeByRole(String role) {

        if(!role.isEmpty()){
            StringBuilder sql=new StringBuilder()
                    .append("SELECT ID_PRIVILEGE FROM ITOB_ROLE_PRIVILEGES WHERE ")
                    .append("UPPER(ID_ROLE) = '")
                    .append(role.toUpperCase()).append("'");
            return jdbcTemplate.queryForList(sql.toString(),String.class);
        }
        return null;
    }

    @Override
    public List<String> findRoleByPrivilege(String privilege){

        if(!privilege.isEmpty()){
            StringBuilder sql=new StringBuilder()
                    .append("SELECT ID_ROLE FROM ITOB_ROLE_PRIVILEGES WHERE ")
                    .append("UPPER(ID_PRIVILEGE) = '")
                    .append(privilege.toUpperCase()).append("'");
            return jdbcTemplate.queryForList(sql.toString(),String.class);
        }
        return null;
    }

}
