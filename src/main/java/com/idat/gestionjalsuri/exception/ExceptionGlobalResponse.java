package com.idat.gestionjalsuri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalResponse {

	@ExceptionHandler(ExceptionService.class)
	public ResponseEntity<ErrorResponse> handlerExceptionService(ExceptionService exs){
			ErrorResponse response=new ErrorResponse(exs.getCodigo(), exs.getMensaje());
			return new ResponseEntity<>(response,exs.getHttpStatus());
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> errorValid(BindException exs){
		return new ResponseEntity(new ErrorResponse("-0", exs.getFieldError().getField().concat(" ").concat(exs.getAllErrors().get(0).getDefaultMessage())),HttpStatus.BAD_REQUEST) ;
	}
}
