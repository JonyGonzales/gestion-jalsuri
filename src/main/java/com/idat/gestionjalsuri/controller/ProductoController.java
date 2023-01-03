package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.service.IProductoService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOPRODUCTO)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@GetMapping
	public ResponseEntity<List<Producto>> listar() {

		return ResponseEntity.ok(this.productoService.listar());

	}

	@PostMapping
	public ResponseEntity<Producto> agregar(@RequestBody ProductoRequest productoRequest) {

		Producto productos = this.productoService.guardar(productoRequest);

		if (productos != null) {
			return ResponseEntity.created(URI.create("/api/v1/productos" + productoRequest)).build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> buscarXId(@PathVariable("id") Long id) throws ExceptionService {
		return new ResponseEntity<>(this.productoService.buscarXid(id),HttpStatus.OK);
	}
	
		
	// Metodo que sirve para eliminar un item
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) throws ExceptionService {
		this.productoService.eliminar(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
