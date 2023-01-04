package com.idat.gestionjalsuri.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ProductoRequest {

	@NotBlank
	private String nombre;
	@Min(0)
	private Integer stock ;
	@Min(0)
	private Double precio;
	
	private Long idCategoria;

	private Long idUnidadMedida;
	private Long idProveedor;


}