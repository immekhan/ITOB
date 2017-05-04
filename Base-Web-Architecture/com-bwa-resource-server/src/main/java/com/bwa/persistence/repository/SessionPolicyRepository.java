package com.bwa.persistence.repository;

import com.bwa.persistence.model.CustomerType;
import com.bwa.persistence.model.SessionPolicy;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface SessionPolicyRepository extends BaseRepository<SessionPolicy,Long> {

    SessionPolicy findByName(String name);
    
}
