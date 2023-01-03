package com.idat.gestionjalsuri.repository;

import com.idat.gestionjalsuri.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	boolean existsByNombre(String nombre);
	boolean existsByEmail(String email);
	boolean existsByDocumento(String email);

}
