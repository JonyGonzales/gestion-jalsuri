package com.idat.gestionjalsuri.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
 
	private String codigo;
	private String mensaje;
	private HttpStatus httpStatus;
	
	
	
	public ErrorResponse() {}
	
	
	
	
	public ErrorResponse(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}




	public ErrorResponse(String codigo, String mensaje, HttpStatus httpStatus) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.httpStatus = httpStatus;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
