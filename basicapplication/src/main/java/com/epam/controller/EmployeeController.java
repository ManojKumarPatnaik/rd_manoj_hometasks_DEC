package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.EmployeeDto;
import com.epam.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/{id}")
	@Operation(description = "Fetches Employee with EmployeeId")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") int empId) {
		return ResponseEntity.ok(employeeService.getEmployee(empId));
	}

	@GetMapping
	@Operation(description = "Fetches All Employees")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
		System.out.println(employeeService.getAllEmployee().get(0));
		return ResponseEntity.ok(employeeService.getAllEmployee());
	}

	@PostMapping
	@Operation(description = "Add An Employee")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.ok("Employee Added Successfully");
	}

	@PutMapping("/{empId}")
	@Operation(description = "Update An Employee")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employeeDto,
			@PathVariable("empId") int empId) {
		employeeDto.setEmpId(empId);
		employeeService.addEmployee(employeeDto);
		return ResponseEntity.ok("Employee Updated Successfully");
	}

	@DeleteMapping("/{empId}")
	@Operation(description = "Delete An Employee (only by ADMIN)")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteEmployee(@PathVariable("empId") int empId) {
		employeeService.deleteEmployee(empId);
		return ResponseEntity.ok("Employee Deleted Successfully");

	}
}
