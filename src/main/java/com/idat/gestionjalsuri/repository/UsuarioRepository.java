package com.idat.gestionjalsuri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.gestionjalsuri.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	

}
