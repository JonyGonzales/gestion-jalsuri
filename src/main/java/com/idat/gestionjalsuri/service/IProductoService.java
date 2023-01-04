package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.request.ProductoRequest;
import com.idat.gestionjalsuri.model.response.DataResponse;

public interface IProductoService {

	Producto insertar (ProductoRequest productoRequest);
	Producto actualizar (ProductoRequest productoRequest);
	DataResponse listar () ;
	Producto buscarXid (Long id) throws ExceptionService;
	void eliminar (Long id) throws ExceptionService;
	
}
