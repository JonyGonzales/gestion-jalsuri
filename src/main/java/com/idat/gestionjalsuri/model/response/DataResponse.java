package com.idat.gestionjalsuri.model.response;

import com.idat.gestionjalsuri.model.entity.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataResponse {
    private List<Producto> productos;
    private Integer totalProducto;
    private Double precioTotalProducto;
}
