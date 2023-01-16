package com.idat.gestionjalsuri.repository;

import com.idat.gestionjalsuri.model.entity.TipoDocumento;
import com.idat.gestionjalsuri.model.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long> {

}
