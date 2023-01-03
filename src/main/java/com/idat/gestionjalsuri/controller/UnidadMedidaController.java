package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.service.IUnidadMedidaService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOUNIDADMEDIDA)
@CrossOrigin(origins = {"http://192.168.3.25:4200","http://localhost:4200","https://jalsuriweb.000webhostapp.com"})
public class UnidadMedidaController {
	
	@Autowired
	private IUnidadMedidaService unidadMedidaService;
	
	@GetMapping
	public ResponseEntity<List<UnidadMedida>> listar() {

		List<UnidadMedida> unidadMedida = this.unidadMedidaService.listar();

		if (unidadMedida.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(unidadMedida);

	}

		// Metodo para buscar por ID
		@GetMapping("/{id}")
		public ResponseEntity<UnidadMedida> buscar(@PathVariable("id") Long id) {
			UnidadMedida um = unidadMedidaService.busca(id);
			if (um == null) {
				um = new UnidadMedida();
			}
			return new ResponseEntity<>(um, HttpStatus.OK);
		}
		@PostMapping
		public ResponseEntity<UnidadMedida>insertar(@RequestBody UnidadMedida unidadMedida){
		return new ResponseEntity<>(this.unidadMedidaService.registrar(unidadMedida),HttpStatus.CREATED);
		}

}
