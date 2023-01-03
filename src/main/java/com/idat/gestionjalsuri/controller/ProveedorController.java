package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.model.entity.Proveedor;
import com.idat.gestionjalsuri.model.request.ProveedorRequest;
import com.idat.gestionjalsuri.service.IProveedorService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJO_PROVEEDOR)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class ProveedorController {

	@Autowired
	private IProveedorService proveedorService;

	@GetMapping
	public ResponseEntity<List<Proveedor>> listar() {
		return ResponseEntity.ok(this.proveedorService.listar());

	}

	@PostMapping
	public ResponseEntity<Proveedor> agregar(@RequestBody @Validated ProveedorRequest request) {

		Proveedor proveedor = this.proveedorService.registrar(request);

		if (proveedor != null) {
			return ResponseEntity.created(URI.create("/" + proveedor)).build();
		}

		return ResponseEntity.notFound().build();
	}

	// Metodo para buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> buscar(@PathVariable("id") Long id) {
		return new ResponseEntity<>(proveedorService.busca(id), HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Proveedor> actualizarProveedorxId(@PathVariable Long id,
			@RequestBody @Validated ProveedorRequest request) {

		return ResponseEntity.ok(proveedorService.modificar(id,request));

	}



	// Metodo que sirve para eliminar un item
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarProveedor(@PathVariable("id") Long id) {
		return ResponseEntity.ok(proveedorService.eliminar(id));
	}

}
