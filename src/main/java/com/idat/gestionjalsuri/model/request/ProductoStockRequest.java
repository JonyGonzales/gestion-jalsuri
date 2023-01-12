package com.idat.gestionjalsuri.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Builder
@Data
public class ProductoStockRequest {
    @Min(1)
    private Long id;
    @Min(1)
    private Integer stock;

}
