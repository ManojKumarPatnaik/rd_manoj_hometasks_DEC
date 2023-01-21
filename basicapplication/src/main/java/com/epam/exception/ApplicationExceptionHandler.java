package com.epam.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.dto.ExceptionResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = WrongCommandException.class)
	public ResponseEntity<ExceptionResponse> handleWrongCommandException(WrongCommandException exception,
			WebRequest request) {
		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(exception.getMessage());
		exRes.setStatus(HttpStatus.BAD_REQUEST.name());
		exRes.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a")));
		exRes.setPath(request.getDescription(false));
		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeException(EmployeeNotFoundException exception, WebRequest request) {
		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(exception.getMessage());
		exRes.setStatus(HttpStatus.BAD_REQUEST.name());
		exRes.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a")));
		exRes.setPath(request.getDescription(false));
		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}
}
