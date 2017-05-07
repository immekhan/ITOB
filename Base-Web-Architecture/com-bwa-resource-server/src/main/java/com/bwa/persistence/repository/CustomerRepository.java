package com.bwa.persistence.repository;

import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends BaseRepository<Customer,Long> , CustomerRepositoryCustom {

    @Query("select c from Customer c where c.id = :id and c.orgUnit.id = :orgUnitId")
    Customer findById( @Param("id") Long id,
                       @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.userId = :userId and c.orgUnit.id = :orgUnitId")
    Customer findByUserId(@Param("userId") String userId,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.mobileNo = :mobileNo and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNo(@Param("mobileNo") String mobileNo,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.mobileNo = :mobileNo and c.userId = :userId and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNoAndUserId(@Param("mobileNo") String mobileNo,
                                       @Param("userId") String userId,
                                       @Param("orgUnitId") String orgUnitId);


}
