package com.idat.gestionjalsuri.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Generico {

	@Column (name="estado",nullable = false, length = 11)
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	} 
	
	
	
}
