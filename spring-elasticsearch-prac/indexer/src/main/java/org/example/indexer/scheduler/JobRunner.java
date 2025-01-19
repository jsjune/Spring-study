package org.example.indexer.scheduler;

import static org.quartz.JobBuilder.newJob;

import java.util.Map;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public abstract class JobRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        doRun(args);
    }

    protected abstract void doRun(ApplicationArguments args);

    public Trigger buildJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
            .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp))
            .build();
    }

    public JobDetail buildJobDetail(Class<? extends Job> jobClass, String name, String group, Map<String, Object> params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(jobClass)
            .withIdentity(name, group)
            .usingJobData(jobDataMap)
            .build();
    }
}
