package com.itob.app.info;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Hassnan.Ali on 2/14/2017.
 */
@Entity
@Table(name = "JOBS")
public class JobsModelBean {
    private String jobId;
    private String jobTitle;
    private Long minSalary;
    private Long maxSalary;
    private Collection<EmployeesModelBean> employeesByJobId;
    private Collection<JobHistoryModelBean> jobHistoriesByJobId;

    @Id
    @Column(name = "JOB_ID")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "JOB_TITLE")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "MIN_SALARY")
    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    @Basic
    @Column(name = "MAX_SALARY")
    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobsModelBean that = (JobsModelBean) o;

        if (jobId != null ? !jobId.equals(that.jobId) : that.jobId != null) return false;
        if (jobTitle != null ? !jobTitle.equals(that.jobTitle) : that.jobTitle != null) return false;
        if (minSalary != null ? !minSalary.equals(that.minSalary) : that.minSalary != null) return false;
        if (maxSalary != null ? !maxSalary.equals(that.maxSalary) : that.maxSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId != null ? jobId.hashCode() : 0;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (minSalary != null ? minSalary.hashCode() : 0);
        result = 31 * result + (maxSalary != null ? maxSalary.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "jobsByJobId")
    public Collection<EmployeesModelBean> getEmployeesByJobId() {
        return employeesByJobId;
    }

    public void setEmployeesByJobId(Collection<EmployeesModelBean> employeesByJobId) {
        this.employeesByJobId = employeesByJobId;
    }

    @OneToMany(mappedBy = "jobsByJobId")
    public Collection<JobHistoryModelBean> getJobHistoriesByJobId() {
        return jobHistoriesByJobId;
    }

    public void setJobHistoriesByJobId(Collection<JobHistoryModelBean> jobHistoriesByJobId) {
        this.jobHistoriesByJobId = jobHistoriesByJobId;
    }
}
