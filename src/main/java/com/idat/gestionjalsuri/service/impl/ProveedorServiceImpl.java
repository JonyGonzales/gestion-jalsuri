package com.idat.gestionjalsuri.service.impl;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Proveedor;
import com.idat.gestionjalsuri.model.request.ProveedorRequest;
import com.idat.gestionjalsuri.repository.ProveedorRepository;
import com.idat.gestionjalsuri.service.IProveedorService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements IProveedorService {


	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Override
	public Proveedor registrar(ProveedorRequest t) {

		if (this.existeNombre(t.getNombre())){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NOMBRE_EXISTE,HttpStatus.BAD_REQUEST);
		}
		if (this.existeDocumento(t.getDocumento())){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_DOCUMENTO_EXISTE,HttpStatus.BAD_REQUEST);
		}
		if (this.existeEmail(t.getEmail())){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_EMAIL_EXISTE,HttpStatus.BAD_REQUEST);
		}

		return this.proveedorRepository.save(this.getProveedor(t));
	}


	@Override
	public Proveedor modificar(Long id,ProveedorRequest t) {
		Proveedor proveedor=new Proveedor();
		Optional<Proveedor>oProveedor=this.proveedorRepository.findById(id);
		if (oProveedor.isPresent()){
			if (this.existeNombre(t.getNombre())||this.existeEmail(t.getEmail())||this.existeDocumento(t.getDocumento())){
				throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NOMBRE_EXISTE,HttpStatus.BAD_REQUEST);
			}
			proveedor.setId(oProveedor.get().getId());
			proveedor.setNombre(t.getNombre());
			proveedor.setDocumento(t.getDocumento());
			proveedor.setTelefono(t.getTelefono());
			proveedor.setEmail(t.getEmail());
			proveedor.setEstado("A");
			return this.proveedorRepository.save(proveedor);
		}else {
			throw  new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO,HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Proveedor> proveedor = this.proveedorRepository.findById(id);
			
		if(proveedor.isPresent()) {
			this.proveedorRepository.deleteById(id);
			return true;
		}else{
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Proveedor busca(Long id) {
		Optional<Proveedor> proveedor = this.proveedorRepository.findById(id);
		
		if(proveedor.isPresent()) {
			return proveedor.get();
		}else{
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.MENSAGE_NO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Proveedor> listar() {
		List<Proveedor>proveedors= this.proveedorRepository.findAll()
				.stream()
				.filter(c->c.getEstado().equalsIgnoreCase("A"))
				.collect(Collectors.toList());
		if (proveedors.isEmpty()){
			throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO,Constante.LISTA_VACIA,HttpStatus.NOT_FOUND);
		}
		proveedors.sort(Comparator.comparing(Proveedor::getId)
				.reversed());
		return proveedors;
	}

	@Override
	public Page<Proveedor> listarPagina(Pageable page) {
		return this.proveedorRepository.findAll(page);
	}

	private Proveedor getProveedor(ProveedorRequest t) {
		Proveedor proveedor = new Proveedor() ;
		proveedor.setNombre(t.getNombre());
		proveedor.setDocumento(t.getDocumento());
		proveedor.setTelefono(t.getTelefono());
		proveedor.setEmail(t.getEmail());
		proveedor.setEstado("A");

		return proveedor;
	}

	private boolean existeNombre(String t){
		return this.proveedorRepository.existsByNombre(t);
	}
	private boolean existeEmail(String t){
		return this.proveedorRepository.existsByEmail(t);
	}
	private boolean existeDocumento(String t){
		return this.proveedorRepository.existsByDocumento(t);
	}
}
