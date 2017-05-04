package com.bwa.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends Repository<T,ID>{
//made this repository a base repository to use this custom repository as base which have these 4 abstract methods
    void delete(T deleted);

    List<T> findAll();

    /*todo for future purpose
    @Query("select t from #{#entityName} t where t.orgUnit.id = :orgUnitId")
    List<T> findAll(@Param("orgUnitId") String orgUnitId);*/

    Optional<T> findOne(ID id);

    T save(T persisted);

    /*
       todo for future purpose
    @Query("select t from #{#entityName} t where t.id = :id and t.orgUnit.id = :orgUnitId")
    T findById(@Param("id") ID id ,
               @Param("orgUnitId") String orgUnitId);
    */

}
