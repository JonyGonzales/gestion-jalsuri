package com.idat.gestionjalsuri.service.impl;

import com.idat.gestionjalsuri.model.entity.TipoMovimiento;
import com.idat.gestionjalsuri.repository.TipoMovimientoRepository;
import com.idat.gestionjalsuri.service.ITipoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoMovimientoServiceImpl implements ITipoMovimientoService {
    @Autowired
    private TipoMovimientoRepository repository;

    @Override
    public TipoMovimiento registrar(TipoMovimiento tipoMovimiento) {
        return null;
    }

    @Override
    public TipoMovimiento modificar(TipoMovimiento tipoMovimiento) {
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }

    @Override
    public TipoMovimiento busca(Long id) {
        return null;
    }

    @Override
    public List<TipoMovimiento> listar() {
        return this.repository.findAll();
    }

    @Override
    public Page<TipoMovimiento> listarPagina(Pageable page) {
        return null;
    }
}
