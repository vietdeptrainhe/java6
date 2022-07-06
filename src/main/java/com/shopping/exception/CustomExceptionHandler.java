package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopping.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ClassNotFoundException.class)
	public ErrorResponse handlerNotFoundException(Exception ex) {
		ex.printStackTrace();
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Not found exception!");
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> NullPointerException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(500).body("Null Exception");
	}
}
