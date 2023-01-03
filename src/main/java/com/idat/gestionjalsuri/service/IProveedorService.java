package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.Proveedor;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;
import com.idat.gestionjalsuri.model.request.ProveedorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProveedorService {
	

	public Proveedor registrar(ProveedorRequest t) ;

	
	public Proveedor modificar(Long id,ProveedorRequest t) ;

	
	public boolean eliminar(Long id) ;

	
	public Proveedor busca(Long id) ;

	
	public List<Proveedor> listar() ;

	
	public Page<Proveedor> listarPagina(Pageable page);

}
