package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.model.entity.DetalleMovimiento;
import com.idat.gestionjalsuri.model.request.DetalleMovimientoRequest;
import com.idat.gestionjalsuri.service.IDetalleNovimintoService;
import com.idat.gestionjalsuri.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJO_DET_MOV)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class DetalleMovimientoController {

	@Autowired
	private IDetalleNovimintoService detalleNovimintoService;

	@GetMapping
	public ResponseEntity<List<DetalleMovimiento>> listar() {
		return ResponseEntity.ok(this.detalleNovimintoService.listar());

	}

	@PostMapping
	public ResponseEntity<Void> agregar(@RequestBody @Validated DetalleMovimientoRequest request) {
		log.info("MovAlmacenRequest: {}",request);
		log.info("MovAlmacenRequest: {}",request.getObsevacionMovimiento());
		this.detalleNovimintoService.registrar(request);
		return ResponseEntity.ok().build();
	}


	@GetMapping("/{id}")
	public ResponseEntity<DetalleMovimiento> buscar(@PathVariable("id") Long id) {
		return new ResponseEntity<>(detalleNovimintoService.busca(id), HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<DetalleMovimiento> actualizarUsuarioxId(@PathVariable Long id,
			@RequestBody @Validated DetalleMovimientoRequest request) {
		detalleNovimintoService.modificar(id,request);
		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
		return ResponseEntity.ok(detalleNovimintoService.eliminar(id));
	}

}
