package org.example.springbatchprac.batch.listener;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LocalDateTime startTime = jobExecution.getStartTime();
        LocalDateTime endTime = jobExecution.getEndTime();
        if (startTime != null && endTime != null) {
            long time = Duration.between(startTime, endTime).toMillis();
            log.info("Job {} finished in {}ms", jobExecution.getJobInstance().getJobName(), time);
        } else {
            log.warn("Job {} finished without start and end time", jobExecution.getJobInstance().getJobName());
        }
    }
}
