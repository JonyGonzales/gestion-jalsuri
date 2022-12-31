package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;
import com.idat.gestionjalsuri.service.ICategoriaService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOCATEGORIA)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {

		List<Categoria> categoria = this.categoriaService.listar();

		if (categoria.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(categoria);

	}

	@PostMapping
	public ResponseEntity<Categoria> agregar(@RequestBody CategoriaRequest categoriaReq) {

		Categoria categoria = this.categoriaService.registrar(categoriaReq);

		if (categoria != null) {
			return ResponseEntity.created(URI.create("/" + categoria)).build();
		}

		return ResponseEntity.notFound().build();
	}

	// Metodo para buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
		Categoria cat = categoriaService.busca(id);
		if (cat == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarUsuarioxId(@PathVariable Long id,
			@RequestBody CategoriaRequest categoriaRequest) {

		Categoria categoria = categoriaService.busca(id);
		if (categoria == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Categoria usuarioActualizado = categoriaService.modificar(categoriaRequest);
		return ResponseEntity.ok(usuarioActualizado);

	}

	@PutMapping("/cambiaEstado/{id}")
	public ResponseEntity<Categoria> cambiaEstadoXId(@PathVariable Long id, @RequestBody  CategoriaRequest categoriaRequest) {

		Categoria categoria = categoriaService.busca(id);
		if (categoria == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		categoria.setEstado(categoriaRequest.getEstado());
		Categoria usuarioActualizado = categoriaService.modificar(categoriaRequest);
		return ResponseEntity.ok(usuarioActualizado);

	}

	// Metodo que sirve para eliminar un item
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
		return ResponseEntity.ok(categoriaService.eliminar(id));
	}

}
