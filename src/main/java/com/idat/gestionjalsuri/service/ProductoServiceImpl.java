package com.idat.gestionjalsuri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService{

	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public Producto registrar(Producto t) {
		
		return this.productoRepository.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return this.productoRepository.save(t);
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Producto> producto =this.productoRepository.findById(id);
		
		if(producto.isPresent()) {
			return true;
		}
		
		return false;
	}

	@Override
	public Producto busca(Long id) {
		Optional<Producto> producto = this.productoRepository.findById(id);
		
		if(producto.isPresent()) {
			return producto.get();
		}
		
		return null;
	}

	@Override
	public List<Producto> listar() {
		return this.productoRepository.findAll();
	}

	@Override
	public Page<Producto> listarPagina(Pageable page) {
		return this.productoRepository.findAll(page);
	}

}
