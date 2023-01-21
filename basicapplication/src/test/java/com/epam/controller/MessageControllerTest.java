package com.epam.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.epam.exception.WrongCommandException;
import com.epam.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="abc",roles={"ADMIN"})
class MessageControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MessageService messageService;

	ObjectMapper objectMapper;

	String dateTime;

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetMessage() throws Exception {
		when(messageService.getDateTime(anyString())).thenReturn(dateTime);
		mockMvc.perform(get("/message/India").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(dateTime))).andExpect(status().isOk());

	}
	
	@Test
	void testGetMessageException() throws Exception {
		when(messageService.getDateTime(anyString())).thenThrow(WrongCommandException.class);
		mockMvc.perform(get("/message/India").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(dateTime))).andExpect(status().isBadRequest());

	}
	
	

}
