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
	private Integer cantidadProdocto;
	private String obsevacionMovimiento;
	@ManyToOne()
	@JoinColumn(name = "id_mov_almacen")
	private MovAlmacen movAlmacen;

	@ManyToOne()
	@JoinColumn(name = "id_producto")
	private Producto producto;
	private String estado;
}
