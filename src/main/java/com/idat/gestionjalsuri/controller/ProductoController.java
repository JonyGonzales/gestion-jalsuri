package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.request.ProductoRequest;
import com.idat.gestionjalsuri.model.response.DataResponse;
import com.idat.gestionjalsuri.service.IProductoService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOPRODUCTO)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@GetMapping
	public ResponseEntity<DataResponse> listar() {
		return ResponseEntity.ok(this.productoService.listar());

	}

	@PostMapping
	public ResponseEntity<Producto> agregar(@RequestBody @Validated ProductoRequest productoRequest) {
		return new ResponseEntity<>(this.productoService.insertar(productoRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> buscarXId(@PathVariable("id") Long id) throws ExceptionService {
		return new ResponseEntity<>(this.productoService.buscarXid(id),HttpStatus.OK);
	}
	
		

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) throws ExceptionService {
		this.productoService.eliminar(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
