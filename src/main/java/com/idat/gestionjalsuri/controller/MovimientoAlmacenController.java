package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.MovAlmacen;
import com.idat.gestionjalsuri.model.request.MovAlmacenRequest;
import com.idat.gestionjalsuri.model.request.MoverProductoRequest;
import com.idat.gestionjalsuri.model.response.GenericResponse;
import com.idat.gestionjalsuri.service.IMovAlmacenService;
import com.idat.gestionjalsuri.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
	public ResponseEntity<Void> agregar(@RequestBody @Validated MovAlmacenRequest request) {
		log.info("MovAlmacenRequest: {}",request);
		log.info("MovAlmacenRequest: {}",request.getObsevacionMovimiento());
		this.movAlmacenService.registrar(request);
		return ResponseEntity.ok().build();
	}


	@GetMapping("/{id}")
	public ResponseEntity<MovAlmacen> buscar(@PathVariable("id") String id) {
		this.validarNumerico(id);
		return new ResponseEntity<>(movAlmacenService.busca(Long.valueOf(id)), HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Void> actualizarUsuarioxId(@PathVariable Long id,
			@RequestBody @Validated MovAlmacenRequest request) {

		movAlmacenService.modificar(id,request);
		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
		return ResponseEntity.ok(movAlmacenService.eliminar(id));
	}

	@PostMapping ("/mover/{id}")
	public ResponseEntity<GenericResponse> moverProducto(@PathVariable("id")Long id, @RequestBody  @Validated MoverProductoRequest request){
		return ResponseEntity.ok(this.movAlmacenService.moverProducto(id,request));
	}
	private void validarNumerico(String id){
		if (!StringUtils.isNumeric(String.valueOf(id))){
			throw new ExceptionService("-2","Error data request id",HttpStatus.BAD_REQUEST);
		}
	}

}
