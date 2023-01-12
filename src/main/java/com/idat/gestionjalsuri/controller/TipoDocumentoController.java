package com.idat.gestionjalsuri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.model.entity.TipoDocumento;
import com.idat.gestionjalsuri.service.ITipoDocumentoService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOTIPODOCUMENTO)
@CrossOrigin(origins = {"http://192.168.3.25:4200","http://localhost:4200","https://jalsuriweb.000webhostapp.com"})
public class TipoDocumentoController{
	
	@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	@GetMapping
	public ResponseEntity<List<TipoDocumento>> listar() {

		List<TipoDocumento> tipoDocumento = this.tipoDocumentoService.listar();

		if (tipoDocumento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(tipoDocumento);

	}

}
