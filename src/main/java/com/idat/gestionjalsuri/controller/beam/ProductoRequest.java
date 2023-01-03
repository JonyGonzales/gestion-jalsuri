package com.idat.gestionjalsuri.controller.beam;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoRequest {

	
	private String nombre;
	
	private Integer stock ;
	
	private Double precio;
	
	private Long categoria;

	private Long unidadMedida;
	private Long proveedor;


}