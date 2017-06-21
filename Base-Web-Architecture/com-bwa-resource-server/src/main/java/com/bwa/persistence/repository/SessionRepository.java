package com.bwa.persistence.repository;

import com.bwa.persistence.model.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends BaseRepository<Session,String> {

    @Query("select s from Session s where s.customerId = :customerId and s.datLogOff is null")
    List<Session> findActiveSessionsByCustomerId(@Param("customerId") Long customerId);

    @Query("select s from Session s where s.datLogOff is null")
    List<Session> findAllActiveSessions();

}
