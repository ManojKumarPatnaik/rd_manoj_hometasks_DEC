package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.convertor.EmployeeConvertor;
import com.epam.dto.EmployeeDto;
import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeConvertor employeeConvertor;
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeConvertor employeeConvertor) {
		this.employeeRepository=employeeRepository;
		this.employeeConvertor=employeeConvertor;
	}

	public EmployeeDto getEmployee(int empId) {
		return employeeConvertor.toDto(employeeRepository.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee not found with EmployeeId:"+empId)));
	}

	public List<EmployeeDto> getAllEmployee() {
		List<Employee> emList= employeeRepository.findAll();
		if(emList.isEmpty())
		{
			throw new EmployeeNotFoundException("No Employee is registered yet!");
		}
		return employeeConvertor.toDtoList(emList);
	}

	public void addEmployee(EmployeeDto employee) {
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		employee.setPassword(encoder.encode(employee.getDob().toString()));
		employeeRepository.save(employeeConvertor.toEntity(employee));		
	}

	public void deleteEmployee(int empId) {
		employeeRepository.deleteById(empId);		
	}

}