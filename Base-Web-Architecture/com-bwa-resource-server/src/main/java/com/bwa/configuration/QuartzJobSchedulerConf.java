package com.bwa.configuration;

import com.bwa.jobs.MyJobThreeComplexJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.bwa.jobs")
public class QuartzJobSchedulerConf {
    //http://www.concretepage.com/spring-4/spring-4-quartz-2-scheduler-integration-annotation-example-using-javaconfig
    //todo make common function for simple jobs to set attributes
    //todo make common function for complex jobs to set attributes

    @Bean //this is simple job1
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("jobone");
        obj.setTargetMethod("myTask");
        return obj;
    }
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        //This trigger will schedule the job after 3 seconds and repeat after every 30 seconds for 3+1 times.
        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setRepeatInterval(30000);
        stFactory.setRepeatCount(3);//todo repeat counter remove or use
        return stFactory;
    }

    @Bean //this is simple job2
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean2() {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("jobtwo");
        obj.setTargetMethod("myTask");
        return obj;
    }
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean2(){
        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean2().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setRepeatInterval(30000);
        stFactory.setRepeatCount(3);//todo repeat counter remove or use
        return stFactory;
    }
    @Bean//this is complex job1
    public JobDetailFactoryBean jobDetailFactoryBean(){
        /*To pass the parameter to job by JavaConfig, we can have setter method and
        the property should be configured with setJobDataAsMap() in JobDetailFactoryBean
         configuration in JavaConfig*/
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(MyJobThreeComplexJob.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "RAM");
        map.put(MyJobThreeComplexJob.COUNT, 1);
        factory.setJobDataAsMap(map);
        factory.setGroup("mygroup");
        factory.setName("myjob");
        return factory;
    }
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(simpleTriggerFactoryBean().getObject(), simpleTriggerFactoryBean2().getObject(),cronTriggerFactoryBean().getObject());
        return scheduler;
    }
}
