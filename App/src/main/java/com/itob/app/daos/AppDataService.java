package com.itob.app.daos;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.itob.app.idaos.CustomDepartmentRepository;
import com.itob.app.info.Department;
import com.itob.app.info.DepartmentsModelBean;
import com.itob.app.repositories.DepartmentRepository;

public class AppDataService implements CustomDepartmentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public int getDeptCount() throws Exception {
		String sql = "select count(*) from departments";
		@SuppressWarnings("deprecation")
		int resultCount = jdbcTemplate.queryForInt(sql);
		System.out.println("Count is: " + resultCount);
		return resultCount;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Department> getAllDepartments() {
		String sql = "select * from departments";
		List<Department> departments = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Department.class));
		System.out.println(departments.size());
		return departments;
	}

	@Override
	public int saveDepartment(Department department) {
		int rowsInserted = 0;
		String sql = "insert into departments (department_id, department_name, manager_id, location_id)"
				+ "values (:deptId, :deptName, :managerId, :locationId)";
		rowsInserted = jdbcTemplate.update(sql, new Object[] { department.getDepartmentId(),
				department.getDepartmentName(), department.getManagerId(), department.getLocationId() });
		System.out.println("Rows Inserted:" + rowsInserted);
		return rowsInserted;
	}

	@Override
	public long findCount() {
		return departmentRepository.count();
	}

	@Override
	public long saveDepartmentModelBean(DepartmentsModelBean departmentModelBean) {
		return departmentRepository.save(departmentModelBean).getDepartmentId();
	}

}
