package org.example.springbatchprac.scheduler;

import java.util.HashMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class FileJobRunner extends JobRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {
        String[] sourceArgs = args.getSourceArgs();
        JobDetail jobDetail = buildJobDetail(FileSchJob.class, "fileJob", "batch", new HashMap<>());
        Trigger trigger = buildJobTrigger("0/50 * * * * ?");
        jobDetail.getJobDataMap().put("requestDate", sourceArgs[0]);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
