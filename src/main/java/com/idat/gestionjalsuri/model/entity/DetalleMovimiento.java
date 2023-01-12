package com.idat.gestionjalsuri.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name="movimiento_almacen" )
public class MovAlmacen extends Generico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoMovimiento;
	private LocalDateTime fechaMovimiento;
	private String obsevacionMovimiento;


    @ManyToOne()
    @JoinColumn(name = "id_tipo_documento")
	private TipoDocumento tipoDocumento;
	
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;
}
