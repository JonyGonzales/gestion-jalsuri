package com.idat.gestionjalsuri.controller;

import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.request.CategoriaRequest;
import com.idat.gestionjalsuri.service.ICategoriaService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOCATEGORIA)
@CrossOrigin(origins = { "http://192.168.3.25:4200", "http://localhost:4200", "https://jalsuriweb.000webhostapp.com" })
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok(this.categoriaService.listar());

	}

	@PostMapping
	public ResponseEntity<Categoria> agregar(@RequestBody @Validated CategoriaRequest categoriaReq) {

		Categoria categoria = this.categoriaService.registrar(categoriaReq);

		if (categoria != null) {
			return ResponseEntity.created(URI.create("/" + categoria)).build();
		}

		return ResponseEntity.notFound().build();
	}

	// Metodo para buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoriaService.busca(id), HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarUsuarioxId(@PathVariable Long id,
			@RequestBody @Validated CategoriaRequest categoriaRequest) {

		return ResponseEntity.ok(categoriaService.modificar(id,categoriaRequest));

	}

	@PutMapping("/cambiaEstado/{id}")
	public ResponseEntity<Categoria> cambiaEstadoXId(@PathVariable Long id, @RequestBody  CategoriaRequest categoriaRequest) {

		Categoria categoria = categoriaService.busca(id);
		if (categoria == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		categoria.setEstado(categoriaRequest.getEstado());
		//Categoria usuarioActualizado = categoriaService.modificar(categoriaRequest);
		return null;//ResponseEntity.ok(usuarioActualizado);

	}

	// Metodo que sirve para eliminar un item
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Long id) {
		return ResponseEntity.ok(categoriaService.eliminar(id));
	}

}
