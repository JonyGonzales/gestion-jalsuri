package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.model.entity.MovAlmacen;
import com.idat.gestionjalsuri.model.request.MovAlmacenRequest;
import com.idat.gestionjalsuri.model.request.MoverProductoRequest;
import com.idat.gestionjalsuri.model.response.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMovAlmacenService {
	

	public void registrar(MovAlmacenRequest t) ;

	
	public void modificar(Long id,MovAlmacenRequest t) ;
	public GenericResponse moverProducto(Long idMovimineto,MoverProductoRequest request) ;

	
	public boolean eliminar(Long id) ;

	
	public MovAlmacen busca(Long id) ;

	
	public List<MovAlmacen> listar() ;

	
	public Page<MovAlmacen> listarPagina(Pageable page);

}
