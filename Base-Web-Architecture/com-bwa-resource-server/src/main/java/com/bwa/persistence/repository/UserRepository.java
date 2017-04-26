package com.bwa.persistence.repository;

import com.bwa.persistence.dao.CustomUserDao;
import com.bwa.persistence.model.UserMBean;

public interface UserRepository extends BaseRepository<UserMBean,Long> , CustomUserDao {

    long count();
}
