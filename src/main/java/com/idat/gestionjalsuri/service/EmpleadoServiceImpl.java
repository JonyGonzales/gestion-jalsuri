package com.idat.gestionjalsuri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.gestionjalsuri.model.entity.Empleado;
import com.idat.gestionjalsuri.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public Empleado registrar(Empleado t) {
		return this.empleadoRepository.save(t);
	}

	@Override
	public Empleado modificar(Empleado t) {
		return this.empleadoRepository.save(t);
	}

	@Override
	public boolean eliminar(Long id) {
		if(id!= null && id>0) {
			this.empleadoRepository.deleteById(id);
			return true;
		}		
		
		return false;
	}

	@Override
	public Empleado busca(Long id) {
	Optional<Empleado> empleado = this.empleadoRepository.findById(id);
		
		if(empleado.isPresent()) {
			return empleado.get();
		}
				
		return null;
	}

	@Override
	public List<Empleado> listar() {
		return this.empleadoRepository.findAll();
	}

	@Override
	public Page<Empleado> listarPagina(Pageable page) {
		return this.empleadoRepository.findAll(page);
	}

}
