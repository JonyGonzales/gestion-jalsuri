package com.idat.gestionjalsuri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;
import com.idat.gestionjalsuri.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Categoria registrar(CategoriaRequest t) {
		Categoria categoria = new Categoria() ;
		
		categoria.setNombre(t.getNombre());
		categoria.setEstado("Activo");
				
		return this.categoriaRepository.save(categoria);
	}

	@Override
	public Categoria modificar(CategoriaRequest t) {
		Categoria categoria = new Categoria() ;
		
		categoria.setNombre(t.getNombre());
		
		return this.categoriaRepository.save(categoria);
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
			
		if(categoria.isPresent()) {
			this.categoriaRepository.deleteById(id);
			return true;
		}		
		return false;
	}

	@Override
	public Categoria busca(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			return categoria.get();
		}
				
		return null;
	}

	@Override
	public List<Categoria> listar() {
		return this.categoriaRepository.findAll();		
	}

	@Override
	public Page<Categoria> listarPagina(Pageable page) {
		return this.categoriaRepository.findAll(page);
	}

}
