package com.singleo.springboot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * spring定时调度多线程
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        //参数传入一个size为20的线程池
        scheduledTaskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(20));
    }
}
