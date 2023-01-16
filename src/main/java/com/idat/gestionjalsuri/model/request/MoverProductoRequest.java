package com.idat.gestionjalsuri.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Data
public class MoverProductoRequest {
    @Min(1)
    private Long idProducto;
    @Min(1)
    private Integer cantidadMovimiento;
    @NotBlank
    private String obsevacionMovimiento;
    @Min(1)
    private Long tipoDocumento;
    @Min(1)
    private Long usuario;
    @Min(1)
    private Long tipoMovimiento;
}
