package com.bosonit.formacion.block17springBatch.error;

import com.bosonit.formacion.block17springBatch.batch.WeatherProcessor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErrorDecider implements JobExecutionDecider {
    @Autowired
    private WeatherProcessor itemProcessor;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        int totalErrors = itemProcessor.getErrorCount();
        if (totalErrors > 100) {
            return new FlowExecutionStatus("FAILED");
        } else {
            return new FlowExecutionStatus("COMPLETED");
        }
    }
}
