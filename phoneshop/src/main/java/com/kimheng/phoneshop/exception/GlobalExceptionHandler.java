package com.kimheng.phoneshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e){
		ErrorRespone errorBody = new ErrorRespone(e.getStatus(), e.getMessage());
		return ResponseEntity.status(e.getStatus()).body(errorBody);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e){
		ErrorRespone errorBody = new ErrorRespone(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
	}
}
