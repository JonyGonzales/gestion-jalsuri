package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.model.entity.MovAlmacen;
import com.idat.gestionjalsuri.model.request.MovAlmacenRequest;
import com.idat.gestionjalsuri.service.IMovAlmacenService;
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
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJO_MOV_ALMACEN)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class MovimientoAlmacenController {

	@Autowired
	private IMovAlmacenService movAlmacenService;

	@GetMapping
	public ResponseEntity<List<MovAlmacen>> listar() {
		return ResponseEntity.ok(this.movAlmacenService.listar());

	}

	@PostMapping
	public ResponseEntity<MovAlmacen> agregar(@RequestBody @Validated MovAlmacenRequest request) {
		log.info("MovAlmacenRequest: {}",request);
		log.info("MovAlmacenRequest: {}",request.getObsevacionMovimiento());
		return ResponseEntity.ok(this.movAlmacenService.registrar(request));
	}


	@GetMapping("/{id}")
	public ResponseEntity<MovAlmacen> buscar(@PathVariable("id") Long id) {
		return new ResponseEntity<>(movAlmacenService.busca(id), HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<MovAlmacen> actualizarUsuarioxId(@PathVariable Long id,
			@RequestBody @Validated MovAlmacenRequest request) {
		return ResponseEntity.ok(movAlmacenService.modificar(id,request));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
		return ResponseEntity.ok(movAlmacenService.eliminar(id));
	}

}
