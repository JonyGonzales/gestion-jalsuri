package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// Metodo para buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
		Categoria cat = categoriaService.busca(id);
		if (cat == null) {
			cat = new Categoria();
		}
		return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarUsuarioxId(@PathVariable Long id, @RequestBody Categoria detalleUsuario) {
		Categoria usuario = categoriaService.busca(id);

		usuario.setNombre(detalleUsuario.getNombre());

		Categoria usuarioActualizado = categoriaService.modificar(usuario);
		return ResponseEntity.ok(usuarioActualizado);

	}

	@PutMapping("/cambiaEstado/{id}")
	public ResponseEntity<Categoria> cambiaEstadoXId(@PathVariable Long id, @RequestBody Categoria detalleUsuario) {
		Categoria usuario = categoriaService.busca(id);
		usuario.setEstado(detalleUsuario.getEstado());

		Categoria usuarioActualizado = categoriaService.modificar(usuario);
		return ResponseEntity.ok(usuarioActualizado);

	}
	
		// Metodo que sirve para eliminar un item
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable("id") Long id) {
			categoriaService.eliminar(id);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar", Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
		}

}
