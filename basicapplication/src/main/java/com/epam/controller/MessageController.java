package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.Message;
import com.epam.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;

	@GetMapping("/{countryName}")
	@Operation(description = "Fetches DateTime of particular ZoneId(India/Eastern USA)")
	@ApiResponse(responseCode = "200", description = "Ok")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	public Message getMessage(@PathVariable("countryName") String countryName) {
		return new Message("Hello World",messageService.getDateTime(countryName));
	}

}
