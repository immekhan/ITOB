package com.bwa.persistence.repository;

import com.bwa.persistence.model.RolePrivilege;
import com.bwa.persistence.repository.custom.RolePrivilegeRepositoryCustom;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface RolePrivilegeRepository extends BaseRepository<RolePrivilege,RolePrivilege.RolePrivilegePk> , RolePrivilegeRepositoryCustom {


}
