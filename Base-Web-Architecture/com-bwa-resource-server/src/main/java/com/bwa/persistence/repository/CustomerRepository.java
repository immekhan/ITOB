package com.bwa.persistence.repository;

import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends BaseRepository<Customer,Long> , CustomerRepositoryCustom {

    @Query("select c from Customer c join fetch c.customerType customerType join fetch customerType.sessionPolicy join fetch c.orgUnit where c.id = :id and c.orgUnit.id = :orgUnitId")
    Customer findById( @Param("id") Long id,
                       @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c join fetch c.customerType customerType join fetch customerType.sessionPolicy join fetch c.orgUnit  where c.userId = :userId and c.orgUnit.id = :orgUnitId")
    Customer findByUserId(@Param("userId") String userId,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c join fetch c.customerType customerType join fetch customerType.sessionPolicy join fetch c.orgUnit  where c.mobileNo = :mobileNo and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNo(@Param("mobileNo") String mobileNo,
                            @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c join fetch c.customerType customerType join fetch customerType.sessionPolicy join fetch c.orgUnit  where c.mobileNo = :mobileNo and c.userId = :userId and c.orgUnit.id = :orgUnitId")
    Customer findByMobileNoAndUserId(@Param("mobileNo") String mobileNo,
                                       @Param("userId") String userId,
                                       @Param("orgUnitId") String orgUnitId);

    @Query("select c from Customer c join fetch c.customerType customerType join fetch customerType.sessionPolicy join fetch c.orgUnit  where c.email = :emailId and c.orgUnit.id = :orgUnitId")
    Customer findByEmailId(@Param("emailId") String emailId,
                          @Param("orgUnitId") String orgUnitId);
}
