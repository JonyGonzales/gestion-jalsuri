package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaServiceImpl implements IUnidadMedidaService{

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;
	
	
	@Override
	public UnidadMedida registrar(UnidadMedida t) {
		return this.unidadMedidaRepository.save(t);
	}

	@Override
	public UnidadMedida modificar(UnidadMedida t) {
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		return false;
	}

	@Override
	public UnidadMedida busca(Long id) {
	Optional<UnidadMedida> unidadMedida = this.unidadMedidaRepository.findById(id);
		
		if(unidadMedida.isPresent()) {
			return unidadMedida.get();
		}
				
		return null;
	}

	@Override
	public List<UnidadMedida> listar() {
		return this.unidadMedidaRepository.findAll();
	}

	@Override
	public Page<UnidadMedida> listarPagina(Pageable page) {
		return this.unidadMedidaRepository.findAll(page);
	}

}
