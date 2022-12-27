package com.idat.gestionjalsuri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.service.IUnidadMedidaService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOUIDADMEDIDA)
@CrossOrigin("http://localhost:4200")
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

}
