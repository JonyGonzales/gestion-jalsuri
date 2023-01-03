package com.idat.gestionjalsuri.model.request;

import com.idat.gestionjalsuri.model.entity.Generico;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter

public class ProveedorRequest extends Generico {

	@NotBlank
	private String nombre;
	@NotBlank

	private String documento;
	@NotBlank
	private String telefono;
	@Email
	private String email;
	

}
