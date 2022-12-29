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

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.service.ICategoriaService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOCATEGORIA)
@CrossOrigin(origins = {"http://192.168.3.25:4200","http://localhost:4200"})
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {

		List<Categoria> categoria = this.categoriaService.listar();

		if (categoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(categoria);

	}

	@PostMapping
	public ResponseEntity<Categoria> agregar(@RequestBody Categoria categoria) {
		categoria.setEstado("Activo");
		Categoria categorias = this.categoriaService.registrar(categoria);

		if (categorias != null) {
			return ResponseEntity.created(URI.create("/" + categoria)).build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	
	
	

}
