package com.bwa.persistence.repository;

import com.bwa.persistence.custom.repository.CustomerRepositoryCustom;
import com.bwa.persistence.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends BaseRepository<Customer,Long> , CustomerRepositoryCustom {

    @Query("select c from Customer c where c.id = :id and c.orgUnit.id = :orgUnitId")
    Customer findById( @Param("id") Long id,
                       @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.userName = :userName and c.orgUnit.id = :orgUnitId")
    Customer findByUserName(@Param("userName") String userName,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.mobileNo = :mobileNo and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNo(@Param("mobileNo") String mobileNo,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c where c.mobileNo = :mobileNo and c.userName = :userName and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNoAndUserName(@Param("mobileNo") String mobileNo,
                                       @Param("userName") String userName,
                                       @Param("orgUnitId") String orgUnitId);


}
