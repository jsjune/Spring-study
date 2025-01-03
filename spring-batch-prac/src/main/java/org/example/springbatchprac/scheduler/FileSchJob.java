package org.example.springbatchprac.scheduler;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileSchJob extends QuartzJobBean {

    private final Job fileJob;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) {
        String requestDate = (String) context.getJobDetail().getJobDataMap().get("requestDate");

        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("id", new Date().getTime())
            .addString("requestDate", requestDate)
            .toJobParameters();

        long jobInstanceCount = jobExplorer.getJobInstanceCount(fileJob.getName());

        if (jobInstanceCount > 0) {
            List<JobInstance> jobInstances = jobExplorer.getJobInstances(fileJob.getName(), 0,
                (int) jobInstanceCount);

            boolean alreadyExists = jobInstances.stream()
                .flatMap(instance -> jobExplorer.getJobExecutions(instance).stream())
                .anyMatch(execution -> execution.getJobParameters().getString("requestDate")
                    .equals(requestDate));

            if (alreadyExists) {
                throw new JobExecutionException(requestDate + " already exists");
            }
        }

        jobLauncher.run(fileJob, jobParameters);
    }
}
