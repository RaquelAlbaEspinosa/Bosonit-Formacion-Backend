package com.bosonit.formacion.block17springBatch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class LoadJobLauncher {
    private final JobLauncher jobLauncher;
    private final Job job;

    public LoadJobLauncher(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public void launchJob() {
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(job, parameters);
            System.out.println("job execution");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



