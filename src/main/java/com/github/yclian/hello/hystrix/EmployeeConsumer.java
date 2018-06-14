package com.github.yclian.hello.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@SpringBootApplication
@Configuration
public class EmployeeConsumer {

  @Bean
  public EmployeeConsumerController employeeConsumerController() { return new EmployeeConsumerController(); }

  public static void main(String[] args) throws RestClientException, IOException {

    ApplicationContext context = new SpringApplicationBuilder(EmployeeConsumer.class).
        properties("spring.application.name=employee-consumer").
        properties("spring.config.location=classpath:/app.employee-consumer.yml").
        web(true).run(args);

    EmployeeConsumerController controller = context.getBean(EmployeeConsumerController.class);
    controller.getEmployee();
  }
}
