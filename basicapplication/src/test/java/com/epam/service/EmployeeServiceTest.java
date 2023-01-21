package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.epam.convertor.EmployeeConvertor;
import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;
	
	@Captor
	ArgumentCaptor<Employee> employeeCaptor;

	Employee employee;
	List<Employee> employees;
	EmployeeConvertor employeeConvertor;

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
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllEmployees() throws EmployeeNotFoundException {
		when(employeeRepository.findAll()).thenReturn(employees);
		assertEquals(1, employeeService.getAllEmployee().size());
	}
	
	@Test
	void testGetAllEmployeeException() throws EmployeeNotFoundException {
		when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(EmployeeNotFoundException.class,()->employeeService.getAllEmployee());
	}

	@Test
	void testGetEmployee() throws EmployeeNotFoundException {
		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
		assertEquals("Sai", employeeService.getEmployee(1).getEmpName());
	}
	
	@Test
	void testGetEmployeeEception() throws EmployeeNotFoundException {
		assertThrows(EmployeeNotFoundException.class,()->employeeService.getEmployee(1));
	}

	@Test
	void testAddEmployee() {
		employeeService.addEmployee(employeeConvertor.toDto(employee));
		verify(employeeRepository,times(1)).save(employeeCaptor.capture());
		assertEquals(employee.getEmpName(), employeeCaptor.getValue().getEmpName());
	}

	@Test
	void testDeleteEmployee() {
		employeeService.deleteEmployee(anyInt());
		verify(employeeRepository,times(1)).deleteById(anyInt());
	}
	
}
