package com.bwa.persistence.repository;

import com.bwa.persistence.model.CustomerType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface CustomerTypeRepository extends BaseRepository<CustomerType,Long> {

}
