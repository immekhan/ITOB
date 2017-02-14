package com.itob.app.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.itob.app.converters.Converter;
import com.itob.app.daos.AppDataService;
import com.itob.app.info.BusinessResponse;
import com.itob.app.info.DepartmentsModelBean;

public class ManagerImpl {

	@Autowired
	private AppDataService appDataService;
	
	@Autowired
	private Converter converter;

	@Transactional
	public BusinessResponse getData() {
		
		//int resultVal = resultVal = appDataService.getDeptCount();
		//BusinessResponse businessResponse = converter.convertDeptCount(resultVal);
		
		//List<Departments> departments = appDataService.getAllDepartments();
		//BusinessResponse businessResponse = converter.convertDeptResponse(departments);
		
/*		Department department = converter.convertRequest("AMR");
		int insertedRows = appDataService.saveDepartment(department);
		BusinessResponse businessResponse = converter.convertInsertedRecord(insertedRows);*/
		
		DepartmentsModelBean department = converter.convertRequestModelBean("XXXX");
		long insertedRows = appDataService.saveDepartmentModelBean(department);
		BusinessResponse businessResponse = converter.convertInsertedRecord((int)insertedRows);
		
		
		return businessResponse;
	}
}
