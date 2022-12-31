package com.idat.gestionjalsuri.exception;

import org.springframework.http.HttpStatus;

public class ExceptionService extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	private final String codigo;
	private final String mensaje;
	private final HttpStatus httpStatus;
	
	
	public ExceptionService(String codigo, String mensaje, HttpStatus httpStatus) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.httpStatus = httpStatus;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	
	
}
