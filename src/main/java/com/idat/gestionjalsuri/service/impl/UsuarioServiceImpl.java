package com.idat.gestionjalsuri.service.impl;

import java.util.List;
import java.util.Optional;

import com.idat.gestionjalsuri.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.model.entity.Usuario;
import com.idat.gestionjalsuri.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario registrar(Usuario t) {
		return this.usuarioRepository.save(t);
	}

	@Override
	public Usuario modificar(Usuario t) {
		return this.usuarioRepository.save(t);
	}

	@Override
	public boolean eliminar(Long id) {
		
		if(id!= null && id>0) {
			this.usuarioRepository.deleteById(id);
			return true;
		}		
		
		return false;
	}

	@Override
	public Usuario busca(Long id) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			return usuario.get();
		}
				
		return null;
	}

	@Override
	public List<Usuario> listar() {
	
		return this.usuarioRepository.findAll();		
	}

	@Override
	public Page<Usuario> listarPagina(Pageable page) {
		return this.usuarioRepository.findAll(page);
	}

}
