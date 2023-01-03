package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.controller.beam.ProductoRequest;
import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.Categoria;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.entity.Proveedor;
import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.repository.CategoriaRepository;
import com.idat.gestionjalsuri.repository.ProductoRepository;
import com.idat.gestionjalsuri.repository.ProveedorRepository;
import com.idat.gestionjalsuri.repository.UnidadMedidaRepository;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto guardar(ProductoRequest productoRequest) {

		Producto producto = new Producto();
		Optional<Categoria> categoria = this.categoriaRepository.findById(productoRequest.getCategoria());
		Optional<Proveedor> proveedor = this.proveedorRepository.findById(productoRequest.getProveedor());
		Optional<UnidadMedida> unidadMedida = this.unidadMedidaRepository.findById(2l);
		if (categoria.isPresent() && unidadMedida.isPresent() && proveedor.isPresent() ) {
			producto.setNombre(productoRequest.getNombre());
			producto.setStock(productoRequest.getStock());
			producto.setPrecio(productoRequest.getPrecio());
			producto.setFechaIngreso(LocalDate.now());
			producto.setFechaVencimiento(LocalDate.now().plusMonths(2));
			producto.setEstado(Constante.ESTADO_ACTIBO);
			producto.setCategoria(categoria.get());
			producto.setUnidadMedida(unidadMedida.get());
			producto.setProveedor(proveedor.get());
			return this.productoRepository.save(producto);
		}else {
			throw new ExceptionService("","",HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<Producto> listar() {
		List<Producto>productos= this.productoRepository.findAll().stream().filter(p -> p.getEstado().equalsIgnoreCase("A")).collect(Collectors.toList());
		if (productos.isEmpty()){
			throw new ExceptionService("-2","Lista vacia",HttpStatus.NOT_FOUND);
		}
		return productos;
	}

	@Override
	public Producto buscarXid(Long id) throws ExceptionService {
		
		Optional<Producto> producto = this.productoRepository.findById(id);
		
		if (producto.isPresent()) {
			return producto.get();
		}else {
			throw new ExceptionService("-1","No se Encontro el Id",HttpStatus.NO_CONTENT); 
		}
	}

	@Override
	public void eliminar(Long id) throws ExceptionService {
		Optional<Producto> producto = this.productoRepository.findById(id);
		if (producto.isPresent()) {
			this.productoRepository.deleteById(id);
		}else {
			throw new ExceptionService("-1","No se Encontro el Id",HttpStatus.NO_CONTENT); 
		}
		

	}

}
