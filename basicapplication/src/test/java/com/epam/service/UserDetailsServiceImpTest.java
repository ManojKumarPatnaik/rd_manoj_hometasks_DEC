package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epam.convertor.EmployeeConvertor;
import com.epam.entity.AuthGroup;
import com.epam.entity.Employee;
import com.epam.repository.AuthGroupRepository;
import com.epam.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImpTest {

	@Mock
	UserDetailsServiceImp userDetailsSevice;

	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	AuthGroupRepository authGroupRepository;
	
	@Captor
	ArgumentCaptor<Employee> employeeCaptor;

	Employee employee;
	List<Employee> employees;
	EmployeeConvertor employeeConvertor;
	
	AuthGroup authGroup;
	List<AuthGroup> authGroups;

	@BeforeEach
	void setUp() throws Exception {
		employeeRepository = mock(EmployeeRepository.class);
		authGroupRepository=mock(AuthGroupRepository.class);
		employeeConvertor = new EmployeeConvertor();

		userDetailsSevice=new UserDetailsServiceImp(employeeRepository,authGroupRepository);
		
		employee = new Employee();
		employee.setEmpId(1);
		employee.setEmpName("Sai");
		employee.setDob("2001-02-14");
		
		employees = new ArrayList<>();
		employees.add(employee);
		
		authGroup=new AuthGroup();
		authGroup.setId(1);
		authGroup.setUsername("sai");
		authGroup.setRole("USER");
		
		authGroups= new ArrayList<AuthGroup>();
		authGroups.add(authGroup);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testloadUserByUsername() {
		
		when(employeeRepository.findByEmpName(anyString())).thenReturn(employee);
		when(authGroupRepository.findByUsername(anyString())).thenReturn(authGroups);
		assertEquals("Sai", userDetailsSevice.loadUserByUsername("Sai").getUsername());
		
		
	}
	
	@Test
	void testloadUserByUsernameForException() {
		assertThrows(UsernameNotFoundException.class,()->userDetailsSevice.loadUserByUsername("sai123"));
	}
	

}
