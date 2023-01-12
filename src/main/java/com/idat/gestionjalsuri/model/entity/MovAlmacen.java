package com.idat.gestionjalsuri.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movimiento_almacen" )
public class MovAlmacen{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipoMovimiento;
	private LocalDateTime fechaMovimiento;
	private String obsevacionMovimiento;
	private int catidadMovimineto;
	@ManyToOne()
	@JoinColumn(name = "id_tipo_documento")
	private TipoDocumento tipoDocumento;
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	private String estado;

}
