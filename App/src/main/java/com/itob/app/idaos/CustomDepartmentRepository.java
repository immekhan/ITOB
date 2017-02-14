package com.itob.app.idaos;

import java.util.List;

import com.itob.app.info.Department;
import com.itob.app.info.DepartmentsModelBean;

public interface CustomDepartmentRepository {
	public int getDeptCount() throws Exception;
	public List<Department> getAllDepartments();
	public int saveDepartment(Department department);
	public long findCount();
	public long saveDepartmentModelBean(DepartmentsModelBean departmentModelBean);

}
