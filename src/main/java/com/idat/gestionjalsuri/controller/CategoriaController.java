package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.service.ICategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
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
		categoria.setEstado("A");
		Categoria categorias = this.categoriaService.registrar(categoria);

		if (categorias != null) {
			return ResponseEntity.created(URI.create("/api/v1/categorias" + categoria)).build();
		}

		return ResponseEntity.notFound().build();
	}

}
