package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.DetalleMovimiento;
import com.idat.gestionjalsuri.model.request.DetalleMovimientoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDetalleNovimintoService {
	

	public void registrar(DetalleMovimientoRequest t) ;

	
	public void modificar(Long id,DetalleMovimientoRequest t) ;

	
	public boolean eliminar(Long id) ;

	
	public DetalleMovimiento busca(Long id) ;

	
	public List<DetalleMovimiento> listar() ;

	
	public Page<Categoria> listarPagina(Pageable page);

}
