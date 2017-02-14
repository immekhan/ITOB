package com.itob.app.info;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by Hassnan.Ali on 2/14/2017.
 */
@Entity
@Table(name = "EMPLOYEES")
public class EmployeesModelBean {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Time hireDate;
    private Long salary;
    private Long commissionPct;
    private Long departmentId;
    private Collection<DepartmentsModelBean> departmentssByEmployeeId;
    private JobsModelBean jobsByJobId;
    private EmployeesModelBean employeesByManagerId;
    private Collection<EmployeesModelBean> employeesByEmployeeId;
    private Collection<JobHistoryModelBean> jobHistoriesByEmployeeId;

    @Id
    @Column(name = "EMPLOYEE_ID")
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "HIRE_DATE")
    public Time getHireDate() {
        return hireDate;
    }

    public void setHireDate(Time hireDate) {
        this.hireDate = hireDate;
    }

    @Basic
    @Column(name = "SALARY")
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "COMMISSION_PCT")
    public Long getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

    @Basic
    @Column(name = "DEPARTMENT_ID")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesModelBean that = (EmployeesModelBean) o;

        if (employeeId != that.employeeId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (hireDate != null ? !hireDate.equals(that.hireDate) : that.hireDate != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;
        if (commissionPct != null ? !commissionPct.equals(that.commissionPct) : that.commissionPct != null)
            return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (commissionPct != null ? commissionPct.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeesByManagerId")
    public Collection<DepartmentsModelBean> getDepartmentssByEmployeeId() {
        return departmentssByEmployeeId;
    }

    public void setDepartmentssByEmployeeId(Collection<DepartmentsModelBean> departmentssByEmployeeId) {
        this.departmentssByEmployeeId = departmentssByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID", nullable = false)
    public JobsModelBean getJobsByJobId() {
        return jobsByJobId;
    }

    public void setJobsByJobId(JobsModelBean jobsByJobId) {
        this.jobsByJobId = jobsByJobId;
    }

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID")
    public EmployeesModelBean getEmployeesByManagerId() {
        return employeesByManagerId;
    }

    public void setEmployeesByManagerId(EmployeesModelBean employeesByManagerId) {
        this.employeesByManagerId = employeesByManagerId;
    }

    @OneToMany(mappedBy = "employeesByManagerId")
    public Collection<EmployeesModelBean> getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Collection<EmployeesModelBean> employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    @OneToMany(mappedBy = "employeesByEmployeeId")
    public Collection<JobHistoryModelBean> getJobHistoriesByEmployeeId() {
        return jobHistoriesByEmployeeId;
    }

    public void setJobHistoriesByEmployeeId(Collection<JobHistoryModelBean> jobHistoriesByEmployeeId) {
        this.jobHistoriesByEmployeeId = jobHistoriesByEmployeeId;
    }
}
