package com.idat.gestionjalsuri.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="proveedores")
public class Proveedor extends Generico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String documento;
	
	private String telefono;
	
	private String email;
	

}
