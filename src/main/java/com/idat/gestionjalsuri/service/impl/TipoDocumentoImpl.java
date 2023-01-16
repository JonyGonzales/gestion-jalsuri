package com.idat.gestionjalsuri.service.impl;

import java.util.List;
import java.util.Optional;

import com.idat.gestionjalsuri.service.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.model.entity.TipoDocumento;
import com.idat.gestionjalsuri.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoImpl implements ITipoDocumentoService {
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public TipoDocumento registrar(TipoDocumento t) {
		return null;
	}

	@Override
	public TipoDocumento modificar(TipoDocumento t) {
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		return false;
	}

	@Override
	public TipoDocumento busca(Long id) {
	Optional<TipoDocumento> tipoDocumento = this.tipoDocumentoRepository.findById(id);
		
		if(tipoDocumento.isPresent()) {
			return tipoDocumento.get();
		}
				
		return null;
	}

	@Override
	public List<TipoDocumento> listar() {
		return this.tipoDocumentoRepository.findAll();
	}

	@Override
	public Page<TipoDocumento> listarPagina(Pageable page) {
		return this.tipoDocumentoRepository.findAll(page);
	}

}
