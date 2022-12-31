package com.idat.gestionjalsuri.controller.beam;


public class ProductoRequest {

	
	private String nombre;
	
	private Double stock ;
	
	private Double precio;
	
	private Long categoria;

	private Long unidadMedida;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public Long getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(Long unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
}