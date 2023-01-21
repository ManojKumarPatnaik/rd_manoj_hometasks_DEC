package com.epam.convertor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.epam.dto.EmployeeDto;
import com.epam.entity.Employee;

@Service
public class EmployeeConvertor {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public Employee toEntity(EmployeeDto employeeDto)
	{
		return modelMapper.map(employeeDto, Employee.class);
	}
	
	public EmployeeDto toDto(Employee employee)
	{
		return modelMapper.map(employee, EmployeeDto.class);
	}
	
	public List<EmployeeDto> toDtoList(List<Employee> employeesList)
	{
		return employeesList.stream().map(employee->modelMapper.map(employee, EmployeeDto.class)).toList();
	}
}
