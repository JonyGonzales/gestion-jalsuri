package com.idat.gestionjalsuri.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Usuario> Usuario = this.usuarioRepository.findById(id);
		
		if(Usuario.isPresent()) {
			return Usuario.get();
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
