package com.bwa.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*If we want to persist the changes in JobDataMap, we will annotate our class by @PersistJobDataAfterExecution */
@PersistJobDataAfterExecution
/*if there is more than one trigger which are scheduling same job then to avoid race condition,
we have to annotate our job with @DisallowConcurrentExecution.*/
@DisallowConcurrentExecution
public class MyJobThreeComplexJob extends QuartzJobBean {
    public static final String COUNT = "count";
    private String name;
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
        int cnt = dataMap.getInt(COUNT);
        JobKey jobKey = ctx.getJobDetail().getKey();
        System.out.println(jobKey+": "+name+": "+ cnt);
        cnt++;
        dataMap.put(COUNT, cnt);
    }
    public void setName(String name) {
        this.name = name;
    }
}