package com.idat.gestionjalsuri.service.generic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICRUD<T> {

	T registrar(T t);
	
	T modificar(T t);
	
	boolean eliminar (Long id);
	
	T busca(Long id);
	
	List<T> listar();
	
	Page<T> listarPagina(Pageable page);
	
	
}
