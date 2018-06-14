package com.github.yclian.hello.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeProducerController {

	private static final Log logger = LogFactory.getLog(EmployeeProducerController.class);

	@GetMapping("/{employeeId}")
	@HystrixCommand(fallbackMethod = "getFallback")
	public Employee get(@PathVariable String employeeId) {

		logger.info("Received: " + employeeId);

		if(!employeeId.equalsIgnoreCase("1")) {
			throw new RuntimeException();
		}

		logger.info("Not falling back!");

		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);

		return emp;
	}

	public Employee getFallback(String employeeId) {
		
		Employee emp = new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);

		return emp;
	}
	
}
