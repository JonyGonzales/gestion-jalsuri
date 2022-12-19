package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.service.ICategoriaService;
import com.idat.gestionjalsuri.service.IProductoService;
import com.idat.gestionjalsuri.service.IUnidadMedidaService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IUnidadMedidaService unidadMedidaService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {

		List<Producto> producto = this.productoService.listar();

		if (producto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(producto);

	}

	
	
	@PostMapping
	public ResponseEntity<Producto> agregar(@RequestBody ProductoRequest productoRequest) {
		
		Producto producto = new Producto() ;
		Categoria categoriaId =this.categoriaService.busca(productoRequest.getIdCategoria());
		UnidadMedida unidadMedidaId =this.unidadMedidaService.busca(productoRequest.getIdUnidadMedida());
		
		
		producto.setNombre(productoRequest.getNombre());
		producto.setStock(productoRequest.getStock());
		producto.setPrecio(productoRequest.getPrecio());
		producto.setFechaIngreso(LocalDate.now());
		producto.setFechaVencimiento(LocalDate.now().plusMonths(2));
		producto.setCategoria(categoriaId);
		producto.setUnidadMedida(unidadMedidaId);		
				
		Producto productos = this.productoService.registrar(producto);

		if (productos != null) {
			return ResponseEntity.created(URI.create("/api/v1/productos" + productoRequest)).build();
		}

		return ResponseEntity.notFound().build();
	}

}
