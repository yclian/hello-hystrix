package com.github.yclian.hello.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableCircuitBreaker
@EnableDiscoveryClient
public class EmployeeProducer {

  @Bean
  public EmployeeProducerController employeeProducerController() { return new EmployeeProducerController(); }


  public static void main(String[] args) {

    new SpringApplicationBuilder(EmployeeProducer.class).
        properties("spring.application.name=employee-producer").
        properties("spring.config.location=classpath:/app.employee-producer.yml").
        web(true).run(args);
  }
}
