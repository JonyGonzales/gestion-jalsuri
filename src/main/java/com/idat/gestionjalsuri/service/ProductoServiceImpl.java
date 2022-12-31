package com.idat.gestionjalsuri.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.repository.CategoriaRepository;
import com.idat.gestionjalsuri.repository.ProductoRepository;
import com.idat.gestionjalsuri.repository.UnidadMedidaRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto guardar(ProductoRequest productoRequest) {

		Producto producto = new Producto();
		Optional<Categoria> categoria = this.categoriaRepository.findById(productoRequest.getCategoria());
		Optional<UnidadMedida> unidadMedida = this.unidadMedidaRepository.findById(productoRequest.getUnidadMedida());
		if (categoria.isPresent() && unidadMedida.isPresent()) {
			producto.setNombre(productoRequest.getNombre());
			producto.setStock(productoRequest.getStock());
			producto.setPrecio(productoRequest.getPrecio());
			producto.setFechaIngreso(LocalDate.now());
			producto.setFechaVencimiento(LocalDate.now().plusMonths(2));
			producto.setEstado("Activo");
			producto.setCategoria(categoria.get());
			producto.setUnidadMedida(unidadMedida.get());
			return this.productoRepository.save(producto);
		}
		
		return null;
		
	}

	@Override
	public List<Producto> listar() {
		return this.productoRepository.findAll().stream().filter(p -> p.getStock() > 0).collect(Collectors.toList());

	}

	@Override
	public Producto buscarXid(Long id) throws ExceptionService {
		
		Optional<Producto> producto = this.productoRepository.findById(id);
		
		if (producto.isPresent()) {
			return producto.get();
		}else {
			throw new ExceptionService("-1","No se Encontro el Id",HttpStatus.NO_CONTENT); 
		}
	}

	@Override
	public void eliminar(Long id) throws ExceptionService {
		Optional<Producto> producto = this.productoRepository.findById(id);
		if (producto.isPresent()) {
			this.productoRepository.deleteById(id);
		}else {
			throw new ExceptionService("-1","No se Encontro el Id",HttpStatus.NO_CONTENT); 
		}
		

	}

}
