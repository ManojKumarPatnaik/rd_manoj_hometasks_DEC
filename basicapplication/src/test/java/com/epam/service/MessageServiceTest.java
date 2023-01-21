package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.exception.WrongCommandException;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

	@Mock
	MessageService messageService;

	@BeforeEach
	void setUp() throws Exception {
		messageService=new MessageService();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetDateTime() {
		ZoneId zoneId = ZoneId.of("Asia/Kolkata");
		assertEquals(messageService.getDateTime("India"),
				LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a")) + " " + zoneId);

	}
	
	@Test
	void testGetDateTime1() {
		ZoneId zoneId = ZoneId.of("America/New_York");
		assertEquals(messageService.getDateTime("Eastern USA"),
				LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a")) + " " + zoneId);

	}
	
	@Test
	void testGetDateTimeException() {
		assertThrows(WrongCommandException.class, ()-> messageService.getDateTime("abc"));
	}

}
