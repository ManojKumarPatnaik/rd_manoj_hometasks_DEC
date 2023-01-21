package com.epam.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.AuthenticationRequest;
import com.epam.dto.EmployeePrincipal;
import com.epam.entity.AuthGroup;
import com.epam.entity.Employee;
import com.epam.service.UserDetailsServiceImp;
import com.epam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthApiControllerTest {

	@Mock
	AuthenticationRequest authenticationRequest;

	@MockBean
	AuthenticationManager authenticationManager;
	
	@MockBean
	UserDetailsServiceImp userDetailsServiceImp;

	@MockBean
	JwtUtil jwtUtil;


	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateAuthenticationToken() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		objectMapper.setDateFormat(format);
		AuthenticationRequest authenticationRequest1 = new AuthenticationRequest();
		authenticationRequest1.setUsername("Sai");
		authenticationRequest1.setPassword("2001-02-14");
		when(authenticationRequest.getUsername()).thenReturn("Sai");
		when(authenticationRequest.getPassword()).thenReturn(authenticationRequest1.getPassword());
		when(userDetailsServiceImp.loadUserByUsername(anyString())).thenReturn(new EmployeePrincipal(new Employee(), new ArrayList<AuthGroup>()));
		mockMvc.perform(post("/employee/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(authenticationRequest1))).andExpect(status().isOk());
		
	}

	@Test
	void testCreateAuthenticationTokenException() throws Exception {
		mockMvc.perform(post("/employee/authenticate").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

}
