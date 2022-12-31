package com.idat.gestionjalsuri.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.repository.CategoriaRepository;
import com.idat.gestionjalsuri.repository.ProductoRepository;
import com.idat.gestionjalsuri.repository.UnidadMedidaRepository;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public Producto guardar(ProductoRequest productoRequest) {
		
		Producto producto = new Producto() ;
		Optional<Categoria> categoria = this.categoriaRepository.findById(productoRequest.getCategoria());
		Optional<UnidadMedida> unidadMedida = this.unidadMedidaRepository.findById(productoRequest.getUnidadMedida());
		if (!categoria.isPresent() && !unidadMedida.isPresent()) {
			return null;
		}
				
		producto.setNombre(productoRequest.getNombre());
		producto.setStock(productoRequest.getStock());
		producto.setPrecio(productoRequest.getPrecio());
		producto.setFechaIngreso(LocalDate.now());
		producto.setFechaVencimiento(LocalDate.now().plusMonths(2));
		producto.setCategoria(categoria.get());
		producto.setUnidadMedida(unidadMedida.get());	
		producto.setEstado("Activo");			
		
		return this.productoRepository.save(producto);
	}

	@Override
	public List<Producto> listar() {
		return this.productoRepository.findAll().stream().filter(p-> p.getStock()>0).collect(Collectors.toList());
		//		return this.productoRepository.findAll().stream().filter(p-> p.estado().equals("A")).collect(Collectors.toList());
	}

	@Override
	public Producto buscarXid(Long id) {
		return this.productoRepository.findById(id).get();
	}

}
