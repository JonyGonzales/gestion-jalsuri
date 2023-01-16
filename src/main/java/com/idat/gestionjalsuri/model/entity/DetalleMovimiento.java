package com.idat.gestionjalsuri.model.entity;

import lombok.*;

import javax.persistence.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="detalle_movimiento" )
public class DetalleMovimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String obsevacionMovimiento;
	private Long idMovAlmacen;
	private Long idProducto;
	private String nombreProducto;
	private String unidadMedida;
	private String estado;
}
