package com.bwa.persistence.repository.custom;

import java.util.List;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface RolePrivilegeRepositoryCustom {

    List<String> findPrivilegeByRole(String role);

    List<String> findRoleByPrivilege(String privilege);
}
