package com.idat.gestionjalsuri.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;

public interface ICategoriaService {
	

	public Categoria registrar(CategoriaRequest t) ;

	
	public Categoria modificar(CategoriaRequest t) ;

	
	public boolean eliminar(Long id) ;

	
	public Categoria busca(Long id) ;

	
	public List<Categoria> listar() ;

	
	public Page<Categoria> listarPagina(Pageable page);

}
