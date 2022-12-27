package com.idat.gestionjalsuri.service;

import java.util.List;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.model.entity.Producto;

public interface IProductoService {

	Producto guardar (ProductoRequest productoRequest);
	List<Producto> listar ();
	Producto buscarXid (Long id);
	
}
