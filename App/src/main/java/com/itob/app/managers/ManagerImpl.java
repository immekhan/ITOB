package com.itob.app.managers;

import org.springframework.beans.factory.annotation.Autowired;

import com.itob.app.converters.Converter;
import com.itob.app.daos.AppDataService;
import com.itob.app.info.BusinessResponse;
import com.itob.app.info.Department;

public class ManagerImpl {

	@Autowired
	private AppDataService appDataService;
	
	@Autowired
	private Converter converter;

	public BusinessResponse getData() {
		
		//int resultVal = resultVal = appDataService.getDeptCount();
		//BusinessResponse businessResponse = converter.convertDeptCount(resultVal);
		
		//List<Departments> departments = appDataService.getAllDepartments();
		//BusinessResponse businessResponse = converter.convertDeptResponse(departments);
		
		Department department = converter.convertRequest("AMR");
		int insertedRows = appDataService.saveDepartment(department);
		BusinessResponse businessResponse = converter.convertInsertedRecord(insertedRows);
		return businessResponse;
	}
}
