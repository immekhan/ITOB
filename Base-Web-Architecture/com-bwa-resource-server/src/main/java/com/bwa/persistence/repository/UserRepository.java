package com.bwa.persistence.repository;

import com.bwa.persistence.model.UserMBean;
import com.bwa.persistence.repository.custom.UserRepositoryCustom;

public interface UserRepository extends BaseRepository<UserMBean,Long> , UserRepositoryCustom {

    long count();
}
