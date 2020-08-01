package me.kalpha;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(BatchApplication.class);
		//app.setWebApplicationType(WebApplicationType.NONE);
		ConfigurableApplicationContext ctl = app.run(args);
	}
}
