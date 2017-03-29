package com.bwa.persistence.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends Repository<T,ID>{
//made this repository a base repository to use this custom repository as base which have these 4 abstract methods
    void delete(T deleted);

    List<T> findAll();

    Optional<T> findOne(ID id);

    T save(T persisted);

}
