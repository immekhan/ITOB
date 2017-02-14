package com.itob.app.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itob.app.info.BusinessResponse;
import com.itob.app.info.Department;
import com.itob.app.info.DepartmentsModelBean;

public class Converter {
	public BusinessResponse getDeptCount(int resultCount) {
		BusinessResponse businessResponse = new BusinessResponse();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalRecord", resultCount);
		businessResponse.setResponseInfo(result);
		return businessResponse;
	}

	public BusinessResponse convertDeptResponse(List<Department> departments) {
		BusinessResponse businessResponse = new BusinessResponse();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalRecord", result.size());
		result.put("data", departments);
		businessResponse.setResponseInfo(result);
		return businessResponse;
	}
	
	public Department convertRequest(String departmentName) {
		Department department = new Department();
		department.setDepartmentId(400L);
		department.setDepartmentName(departmentName);
		return department;
	}
	
	public DepartmentsModelBean convertRequestModelBean(String departmentName) {
		DepartmentsModelBean department = new DepartmentsModelBean();
		department.setDepartmentName(departmentName);
		return department;
	}
	
	public BusinessResponse convertInsertedRecord(int insertedRows) {
		BusinessResponse businessResponse = new BusinessResponse();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("insertedRows", insertedRows);
		businessResponse.setResponseInfo(result);
		return businessResponse;
	}
}
