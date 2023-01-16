package com.idat.gestionjalsuri.model.response;

import com.idat.gestionjalsuri.model.entity.MovAlmacen;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class MovAlmacenResponse {
    List<MovAlmacen>movAlmacens;
    private Long totalProducto;
    private int numeroLista;
}
