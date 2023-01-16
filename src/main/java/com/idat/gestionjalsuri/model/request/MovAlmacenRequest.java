package com.idat.gestionjalsuri.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class MovAlmacenRequest {


    @NotBlank
    private String obsevacionMovimiento;
    private int catidadMovimineto;
    private Long tipoMovimiento;
    private Long tipoDocumento;
    private Long usuario;
}
