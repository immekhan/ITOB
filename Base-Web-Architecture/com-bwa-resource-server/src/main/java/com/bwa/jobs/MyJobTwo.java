package com.bwa.jobs;

import org.springframework.stereotype.Service;

@Service("jobtwo")
public class MyJobTwo {
    protected void myTask() {
    	System.out.println("This is my task 2");
    }
}
