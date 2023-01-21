package com.epam.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.EmployeeDto;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="abc",roles={"ADMIN"})
class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;

	EmployeeDto employeeDto;
	List<EmployeeDto> employeeDtos;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		SimpleDateFormat format=new SimpleDateFormat("2001-02-14");
		objectMapper.setDateFormat(format);

		employeeDto = new EmployeeDto();
		employeeDto.setEmpId(1);
		employeeDto.setEmpName("Sai");
		employeeDto.setAddress("kurnool");
		employeeDto.setDob("2001-02-14");

		employeeDtos = new ArrayList<EmployeeDto>();
		employeeDtos.add(employeeDto);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetEmployee() throws Exception {
		when(employeeService.getEmployee(anyInt())).thenReturn(employeeDto);
		mockMvc.perform(get("/employee/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isOk());
	}

	@Test
	void testGetEmployeeException() throws Exception {
		when(employeeService.getEmployee(anyInt())).thenThrow(EmployeeNotFoundException.class);
		mockMvc.perform(get("/employee/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isBadRequest());
	}

	@Test
	void testGetAllEmployee() throws Exception {
		when(employeeService.getAllEmployee()).thenReturn(employeeDtos);
		mockMvc.perform(get("/employee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDtos))).andExpect(status().isOk());
	}

	@Test
	void testGetAllEmployeeException() throws Exception {
		when(employeeService.getAllEmployee()).thenThrow(EmployeeNotFoundException.class);
		mockMvc.perform(get("/employee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDtos))).andExpect(status().isBadRequest());
	}

	@Test
	void testAddEmployee() throws Exception {
		doNothing().when(employeeService).addEmployee(employeeDto);
		mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isOk());
	}

	@Test
	void testUpdateEmployee() throws Exception {
		doNothing().when(employeeService).addEmployee(employeeDto);
		mockMvc.perform(put("/employee/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isOk());
	}
	
	@Test
	void testDeleteEmployee() throws Exception {
		doNothing().when(employeeService).deleteEmployee(anyInt());
		mockMvc.perform(delete("/employee/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(employeeDto))).andExpect(status().isOk());
	}

}
