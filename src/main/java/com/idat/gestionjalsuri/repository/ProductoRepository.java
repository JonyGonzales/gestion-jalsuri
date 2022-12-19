package com.idat.gestionjalsuri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.gestionjalsuri.model.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	

}
