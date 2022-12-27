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

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> usuario = this.usuarioService.listar();

		if (usuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);

	}

	@PostMapping
	public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
		usuario.setEstado("Activo");
		Usuario usuarios = this.usuarioService.registrar(usuario);

		if (usuarios != null) {
			return ResponseEntity.created(URI.create("/" + usuario)).build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	
	
	

}
