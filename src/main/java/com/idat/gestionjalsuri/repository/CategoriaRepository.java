package com.idat.gestionjalsuri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.gestionjalsuri.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	

}
