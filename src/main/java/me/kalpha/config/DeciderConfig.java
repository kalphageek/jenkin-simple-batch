package me.kalpha.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Configuration
/*
     Step의 결과에 따라 서로 다른 Step으로 이동하는 방법
     Step들의 Flow속에서 분기만 담당하는 별도의 타입(JobExecutionDecider) 이용
 */
public class DeciderConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job deciderJob() {
        return jobBuilderFactory.get("decider")
                .start(startDecider())
                .next(decider()) // 홀수 | 짝수 구분
                .from(decider()) // decider의 상태가
                    .on("EVEN") // EVEN라면
                    .to(evenStep()) // evenStep로 간다.
                .from(decider()) // decider의 상태가
                    .on("ODD") // ODD라면
                    .to(oddStep()) // oddStep로 간다
                .end() // builder 종료
                .build();

    }

    @Bean
    public Step startDecider() {
        return stepBuilderFactory.get("startDecider")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>>> Start Decider");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new OddDecider();
    }


    public static class OddDecider implements JobExecutionDecider {
        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            Random rand = new Random();
            int randomint = rand.nextInt(50) + 1;
            log.info("Random Number : {}", randomint);

            if (randomint % 2 == 0) {
                return new FlowExecutionStatus("EVEN");
            } else {
                return new FlowExecutionStatus("ODD");
            }
        }
    }

    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
                .tasklet((stepContribution, chunkContext) -> {
                    oddStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step evenStep() {
        return stepBuilderFactory.get("evenStep")
                .tasklet((stepContribution, chunkContext) -> {
                    evenStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


}
