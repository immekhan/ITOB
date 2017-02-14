package com.itob.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itob.app.idaos.CustomDepartmentRepository;
import com.itob.app.info.DepartmentsModelBean;

/**
 * Created by Hello Hassnain on 09/02/2017. if we want to expose all of the
 * operation of CurdRepository then CrudRepository is a good option but if we do
 * not want our interface to be litter with unnecessary methods the Repository
 * is a better option
 */

public interface DepartmentRepository extends CrudRepository<DepartmentsModelBean, Long> , CustomDepartmentRepository {
}
