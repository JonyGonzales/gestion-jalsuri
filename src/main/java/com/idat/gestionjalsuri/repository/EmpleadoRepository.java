package com.idat.gestionjalsuri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.gestionjalsuri.model.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
