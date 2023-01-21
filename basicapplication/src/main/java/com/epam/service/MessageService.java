package com.epam.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.epam.exception.WrongCommandException;

@Service
public class MessageService {

	public String getDateTime(String country) {
		
		ZoneId zoneId;
		if (country.equals("Eastern USA")) {
			zoneId = ZoneId.of("America/New_York");
		} else if (country.equals("India")) {
			zoneId = ZoneId.of("Asia/Kolkata");
		} else {
			throw new WrongCommandException("The country name is invalid! Enter the proper country name (Eastern USA or India).");
		}

		return LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a"))+" "+zoneId;
	}
}
