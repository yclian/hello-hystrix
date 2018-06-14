package com.github.yclian.hello.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @see <a href="https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#spring-cloud-running-eureka-server">Spring Cloud: How to run a Eureka Server</a>
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServer.class).
        properties("spring.config.location=classpath:/app.eureka-server.yml").
        web(true).run(args);
	}
}
