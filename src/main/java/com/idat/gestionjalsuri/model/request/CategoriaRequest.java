package com.idat.gestionjalsuri.model.request;

import javax.validation.constraints.NotNull;

public class CategoriaRequest extends GenericoRequest{

	@NotNull
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
