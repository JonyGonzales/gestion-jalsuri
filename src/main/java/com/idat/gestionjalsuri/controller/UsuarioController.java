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

import com.idat.gestionjalsuri.model.entity.Usuario;
import com.idat.gestionjalsuri.service.IUsuarioService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOUSUARIOS)
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("http://192.168.3.25:4200")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	// Metodo para Listar Usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> usuario = this.usuarioService.listar();

		if (usuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);

	}

	// Metodo para Crear Usuarios
	@PostMapping
	public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
		usuario.setEstado("Activo");
		Usuario usuarios = this.usuarioService.registrar(usuario);

		if (usuarios != null) {
			return ResponseEntity.created(URI.create("/" + usuario)).build();
		}

		return ResponseEntity.notFound().build();
	}

	// Metodo para buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable("id") Long id) {
		Usuario cat = usuarioService.busca(id);
		if (cat == null) {
			cat = new Usuario();
		}
		return new ResponseEntity<Usuario>(cat, HttpStatus.OK);
	}

	// Metodo para Actualizar por ID
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuarioxId(@PathVariable Long id, @RequestBody Usuario detalleUsuario) {
		Usuario usuario = usuarioService.busca(id);
		
		usuario.setNombre(detalleUsuario.getNombre());
		usuario.setEmail(detalleUsuario.getEmail());
		usuario.setPassword(detalleUsuario.getPassword());
		usuario.setEstado("Activo");
		
		Usuario usuarioActualizado = usuarioService.modificar(usuario);
		return ResponseEntity.ok(usuarioActualizado);

	}

	@PutMapping("/deshabilitar/{id}")
	public ResponseEntity<Usuario> deshabilitarUsuarioxId(@PathVariable Long id, @RequestBody Usuario detalleUsuario) {
		Usuario usuario = usuarioService.busca(id);
		usuario.setEstado(detalleUsuario.getEstado());

		Usuario usuarioActualizado = usuarioService.modificar(usuario);
		return ResponseEntity.ok(usuarioActualizado);

	}

	// Metodo que sirve para eliminar un item
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable("id") Long id) {
		usuarioService.eliminar(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
