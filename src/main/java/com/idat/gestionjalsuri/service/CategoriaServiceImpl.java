package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;
import com.idat.gestionjalsuri.repository.CategoriaRepository;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Categoria registrar(CategoriaRequest t) {

		if (this.existeNombreCategoria(t)){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NOMBRE_EXISTE,HttpStatus.BAD_REQUEST);
		}
		return this.categoriaRepository.save(this.getCategoria(t));
	}


	@Override
	public Categoria modificar(Long id,CategoriaRequest t) {
		Categoria categoria=new Categoria();
		Optional<Categoria>oCategoria=this.categoriaRepository.findById(id);
		if (oCategoria.isPresent()){
			if (this.existeNombreCategoria(t)){
				throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NOMBRE_EXISTE,HttpStatus.BAD_REQUEST);
			}
			categoria.setId(oCategoria.get().getId());
			categoria.setNombre(t.getNombre());
			categoria.setEstado(Constante.ESTADO_ACTIBO);
			return this.categoriaRepository.save(categoria);
		}else {
			throw  new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO,HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
			
		if(categoria.isPresent()) {
			this.categoriaRepository.deleteById(id);
			return true;
		}else{
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Categoria busca(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			return categoria.get();
		}else{
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria>categorias= this.categoriaRepository.findAll()
				.stream()
				.filter(c->c.getEstado().equalsIgnoreCase("A"))
				.collect(Collectors.toList());
		if (categorias.isEmpty()){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.LISTA_VACIA,HttpStatus.NOT_FOUND);
		}
		categorias.sort(Comparator.comparing(Categoria::getId)
				.reversed());
		return categorias;
	}

	@Override
	public Page<Categoria> listarPagina(Pageable page) {
		return this.categoriaRepository.findAll(page);
	}

	private Categoria getCategoria(CategoriaRequest t) {
		Categoria categoria = new Categoria() ;
		categoria.setNombre(t.getNombre());
		categoria.setEstado("A");

		return categoria;
	}

	private boolean existeNombreCategoria(CategoriaRequest t){
		return this.categoriaRepository.existsByNombre(t.getNombre());
	}

}
