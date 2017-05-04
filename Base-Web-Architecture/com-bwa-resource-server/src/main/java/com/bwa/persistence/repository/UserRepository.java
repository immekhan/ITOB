package com.bwa.persistence.repository;

import com.bwa.persistence.custom.repository.UserRepositoryCustom;
import com.bwa.persistence.model.UserMBean;

public interface UserRepository extends BaseRepository<UserMBean,Long> , UserRepositoryCustom {

    long count();
}
