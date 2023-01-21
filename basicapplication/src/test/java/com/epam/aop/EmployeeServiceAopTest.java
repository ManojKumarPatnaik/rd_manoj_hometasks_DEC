package com.epam.aop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.convertor.EmployeeConvertor;
import com.epam.entity.Employee;
import com.epam.repository.EmployeeRepository;
import com.epam.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeServiceAopTest {
	
	@Mock
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;
	
	@Captor
	ArgumentCaptor<Employee> employeeCaptor;

	Employee employee;
	List<Employee> employees;
	EmployeeConvertor employeeConvertor;
	
	EmployeeService proxyEmployeeService;

	@BeforeEach
	void setUp() throws Exception {
		employeeRepository = mock(EmployeeRepository.class);
		employeeConvertor = new EmployeeConvertor();

		employeeService=new EmployeeService(employeeRepository,employeeConvertor);
		
		employee = new Employee();
		employee.setEmpId(1);
		employee.setEmpName("Sai");
		employee.setDob("2001-02-14");
		
		employees = new ArrayList<>();
		employees.add(employee);
		
		AspectJProxyFactory factory=new AspectJProxyFactory(employeeService);
		EmployeeServiceAop employeeServiceAop=new EmployeeServiceAop();
		factory.addAspect(employeeServiceAop);
		proxyEmployeeService=factory.getProxy();
	}


	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmployeeServiceAop() throws Throwable {
		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
		assertEquals("Sai", proxyEmployeeService.getEmployee(1).getEmpName());	
	}

}
