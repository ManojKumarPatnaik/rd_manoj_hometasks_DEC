
package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee Application", version = "1.0",description = "Employee Catalog"))
public class BasicapplicationApplication {//implements CommandLineRunner {

	/*
	 * @Autowired private EmployeeRepository employeeRepository;
	 * 
	 * @Autowired private AuthGroupRepository authGroupRepository;
	 */

	public static void main(String[] args) {
		SpringApplication.run(BasicapplicationApplication.class, args);

	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * 
	 * PasswordEncoder encoder = new BCryptPasswordEncoder(11);
	 * 
	 * Employee employee = new Employee(); employee.setEmpName("sai");
	 * employee.setAddress("yemmiganur"); employee.setDob("2001-02-14");
	 * employee.setPassword(encoder.encode(employee.getDob().toString()));
	 * employeeRepository.save(employee);
	 * 
	 * AuthGroup authGroup = new AuthGroup(); authGroup.setUsername("sai");
	 * authGroup.setAuthGroup("ADMIN");
	 * 
	 * AuthGroup authGroup1 = new AuthGroup(); authGroup1.setUsername("thanu");
	 * authGroup1.setAuthGroup("USER");
	 * 
	 * authGroupRepository.save(authGroup); authGroupRepository.save(authGroup1);
	 * 
	 * }
	 */

}
