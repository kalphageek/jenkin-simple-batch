package me.kalpha.config;

import lombok.RequiredArgsConstructor;
import me.kalpha.service.Task1Service;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Task1Config {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    Task1Service task1Service;

    @Bean
    public Job task1Job() {
        return jobBuilderFactory.get("task1Job")
                .start(task1Step(null))
                .build();
    }

    @Bean
    @JobScope
    public Step task1Step(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("task1Step")
                .tasklet(((stepContribution, chunkContext) -> {
                    task1Service.task1(requestDate);
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
