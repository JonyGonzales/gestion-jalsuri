package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.service.IProductoService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOPRODUCTO)
@CrossOrigin("http://localhost:4200")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
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
							
		Producto productos = this.productoService.guardar(productoRequest);

		if (productos != null) {
			return ResponseEntity.created(URI.create("/api/v1/productos	" + productoRequest)).build();
		}

		return ResponseEntity.notFound().build();
	}

}
