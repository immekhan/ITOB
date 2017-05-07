package com.bwa.persistence.repository.custom;

import com.bwa.persistence.model.Privilege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface PrivilegeRepositoryCustom {

    List<Privilege> findByRole(String role);

}
