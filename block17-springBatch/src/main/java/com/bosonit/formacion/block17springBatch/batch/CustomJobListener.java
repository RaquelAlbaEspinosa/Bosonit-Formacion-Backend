package com.bosonit.formacion.block17springBatch.batch;

import com.bosonit.formacion.block17springBatch.model.Weather;
import com.bosonit.formacion.block17springBatch.model.WeatherResult;
import com.bosonit.formacion.block17springBatch.model.WeatherRisk;
import com.bosonit.formacion.block17springBatch.repository.WeatherRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherResultRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherRiskRepository;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import java.util.List;


public class CustomJobListener implements JobExecutionListener {
    private final JobOperator jobOperator;
    private final WeatherResultRepository weatherResultRepository;
    private final WeatherRepository weatherRepository;
    private final WeatherRiskRepository weatherRiskRepository;
    private final WeatherWriter weatherWriter;

    public CustomJobListener(JobOperator jobOperator,
                             WeatherResultRepository weatherResultRepository,
                             WeatherRepository weatherRepository,
                             WeatherRiskRepository weatherRiskRepository, WeatherWriter weatherWriter) {
        this.jobOperator = jobOperator;
        this.weatherResultRepository = weatherResultRepository;
        this.weatherRepository = weatherRepository;
        this.weatherRiskRepository = weatherRiskRepository;
        this.weatherWriter = weatherWriter;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            // Obtener las listas de entidades creadas por el job
            List<Weather> createdWeathers = weatherWriter.getCreatedWeathers();
            List<WeatherRisk> createdWeatherRisks = weatherWriter.getCreatedWeatherRisks();
            List<WeatherResult> createdWeatherResult = weatherWriter.getCreatedWeatherResult();

            // Eliminar las entidades creadas manualmente
            for (Weather weather : createdWeathers) {
                weatherRepository.delete(weather);
            }
            for (WeatherRisk weatherRisk : createdWeatherRisks) {
                weatherRiskRepository.delete(weatherRisk);
            }
            for (WeatherResult weatherResult : createdWeatherResult) {
                weatherResultRepository.delete(weatherResult);
            }

            // Detener el job
            try {
                jobOperator.stop(jobExecution.getJobId());
            } catch (NoSuchJobExecutionException e) {
                throw new RuntimeException(e);
            } catch (JobExecutionNotRunningException e) {
                throw new RuntimeException(e);
            }
        }
    }
}