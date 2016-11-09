package com.itob.app.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.itob.app.info.Department;

public class AppDataService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getDeptCount() throws Exception {
		String sql = "select count(*) from departments";
		@SuppressWarnings("deprecation")
		int resultCount = jdbcTemplate.queryForInt(sql);
		System.out.println("Count is: " + resultCount);
		return resultCount;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Department> getAllDepartments() {
		String sql = "select * from departments";
		List<Department> departments = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Department.class));
		System.out.println(departments.size());
		return departments;
	}

	public int saveDepartment(Department department) {
		int rowsInserted = 0;
		String sql = "insert into departments (department_id, department_name, manager_id, location_id)"
				+ "values (:deptId, :deptName, :managerId, :locationId)";
		rowsInserted = jdbcTemplate.update(sql, new Object[] { department.getDepartmentId(),
				department.getDepartmentName(), department.getManagerId(), department.getLocationId() });
		System.out.println("Rows Inserted:" + rowsInserted);
		return rowsInserted;
	}

}
