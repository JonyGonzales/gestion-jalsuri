package com.idat.gestionjalsuri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalResponse {

	@ExceptionHandler(ExceptionService.class)
	public ResponseEntity<ErrorResponse> handlerExceptionService(ExceptionService exs){
			
		return new ResponseEntity<>(new ErrorResponse("-1","Error ", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
