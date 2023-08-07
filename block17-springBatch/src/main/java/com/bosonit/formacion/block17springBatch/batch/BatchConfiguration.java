package com.bosonit.formacion.block17springBatch.batch;

import com.bosonit.formacion.block17springBatch.error.ErrorDecider;
import com.bosonit.formacion.block17springBatch.model.Weather;
import com.bosonit.formacion.block17springBatch.repository.WeatherRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherResultRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherRiskRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.BufferedWriter;

@Configuration
@EnableJpaRepositories(basePackages = "com.bosonit.formacion.block17springBatch.repository")
public class BatchConfiguration {
    @Bean
    @Primary
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    @Bean
    public Step step(FlatFileItemReader<Weather> itemReader,
                     ItemProcessor<Weather, Weather> itemProcessor,
                     ItemWriter<Weather> itemWriter,
                     JobRepository jobRepository,
                     PlatformTransactionManager transactionManager){
        TaskletStep build = new StepBuilder("csv-step", jobRepository)
                .<Weather, Weather>chunk(100, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        System.out.println("este es mi csv-step: " + build);
        return build;
    }
    @Bean
    public Job run(Step step, JobRepository jobRepository, ErrorDecider errorDecider, JobExecutionListener jobListener){
        Job weatherJob = new JobBuilder("weatherJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(step)
                .next(errorDecider)
                .on("FAILED").fail()
                .from(step).on("COMPLETED")
                .end()
                .end()
                .build();
        System.out.println("este es mi weatherJob: " + weatherJob);
        return weatherJob;
    }
    @Bean
    public JobExecutionListener customJobListener(JobOperator jobOperator,
                                                  WeatherResultRepository weatherResultRepository,
                                                  WeatherRepository weatherRepository,
                                                  WeatherRiskRepository weatherRiskRepository,
                                                  WeatherWriter weatherWriter) {
        return new CustomJobListener(jobOperator, weatherResultRepository, weatherRepository,
                weatherRiskRepository, weatherWriter);
    }
    @Bean
    public ItemProcessor<Weather, Weather> weatherProcessor(){
        return new WeatherProcessor();
    }
    @Bean
    public FlatFileItemReader<Weather> doRead() {
        FlatFileItemReader<Weather> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setStrict(false);
        flatFileItemReader.setResource(new FileSystemResource("C:/Users/raquel.alba/IdeaProjects/block17-springBatch/src/main/resources/weather.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(weatherLineMapper());
        System.out.println("este es mi weatherReader: " + flatFileItemReader);
        return flatFileItemReader;
    }
    @Bean
    public DefaultLineMapper<Weather> weatherLineMapper () {
        DefaultLineMapper<Weather> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("City", "Date", "Temperature");

        CsvFieldSetMapper fieldSetMapper = new CsvFieldSetMapper();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        System.out.println("este es mi weatherLineMapper: " + lineMapper);

        return lineMapper;
    }
    @Bean
    public ItemWriter<Weather> write(WeatherRepository weatherRepository, WeatherRiskRepository weatherRiskRepository,
                                     WeatherResultRepository weatherResultRepository) {
        return new WeatherWriter();
    }
}
