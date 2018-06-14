package com.github.yclian.hello.hystrix;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;

@Controller
public class EmployeeConsumerController {

  private static final Log logger = LogFactory.getLog(EmployeeConsumerController.class);

  @Autowired
  private LoadBalancerClient loadBalancer;

  public void getEmployee(int employeeId) throws RestClientException, IOException {

    ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");
    String baseUrl = serviceInstance.getUri().toString() + "/employees/" + employeeId;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = null;

    try {
      response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
    } catch (Exception e) {
      throw new RuntimeException((e));
    }

    logger.info(response.getBody());
  }

  private static HttpEntity<?> getHeaders() throws IOException {

    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

    return new HttpEntity<>(headers);
  }
}
