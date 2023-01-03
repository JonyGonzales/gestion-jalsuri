package com.idat.gestionjalsuri.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
 
	private String codigo;
	private String mensaje;

	
	
	
	public ErrorResponse() {}

	public ErrorResponse(String codigo, String mensaje) {

		this.codigo = codigo;
		this.mensaje = mensaje;
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

	
	
}
