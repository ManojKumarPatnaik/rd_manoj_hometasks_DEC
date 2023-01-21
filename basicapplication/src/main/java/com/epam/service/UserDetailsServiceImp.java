package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.dto.EmployeePrincipal;
import com.epam.entity.AuthGroup;
import com.epam.entity.Employee;
import com.epam.repository.AuthGroupRepository;
import com.epam.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	AuthGroupRepository authGroupRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;
	
	public UserDetailsServiceImp(EmployeeRepository employeeRepository, AuthGroupRepository authGroupRepository) {
		this.authGroupRepository=authGroupRepository;
		this.employeeRepository=employeeRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String empName) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmpName(empName);
		if (null == employee) {
			throw new UsernameNotFoundException("Cannot find username :" + empName);
		}
		
		List<AuthGroup> authorities  = authGroupRepository.findByUsername(empName);

		return new EmployeePrincipal(employee,authorities);
	}

}
