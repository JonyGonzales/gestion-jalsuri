package com.idat.gestionjalsuri.service;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.MovAlmacen;
import com.idat.gestionjalsuri.model.entity.Producto;
import com.idat.gestionjalsuri.model.entity.TipoDocumento;
import com.idat.gestionjalsuri.model.entity.Usuario;
import com.idat.gestionjalsuri.model.request.MovAlmacenRequest;
import com.idat.gestionjalsuri.model.request.MoverProductoRequest;
import com.idat.gestionjalsuri.model.response.GenericResponse;
import com.idat.gestionjalsuri.repository.MovAlmacenRepository;
import com.idat.gestionjalsuri.repository.ProductoRepository;
import com.idat.gestionjalsuri.repository.TipoDocumentoRepository;
import com.idat.gestionjalsuri.repository.UsuarioRepository;
import com.idat.gestionjalsuri.service.IMovAlmacenService;
import com.idat.gestionjalsuri.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovAlmacenServiceImpl implements IMovAlmacenService {

    @Autowired
    private MovAlmacenRepository movAlmacenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoDocumentoRepository documentoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void registrar(MovAlmacenRequest t) {
        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findById(t.getUsuario())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de usuario no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoDocumento> tipoDocumento = Optional.of(this.documentoRepository.findById(t.getTipoDocumento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo documento no encontrado...", HttpStatus.NOT_FOUND)));

        MovAlmacen movAlmacen = MovAlmacen.builder()
                .tipoMovimiento(t.getTipoMovimiento())
                .fechaMovimiento(LocalDateTime.now())
                .obsevacionMovimiento(t.getObsevacionMovimiento())
                .tipoDocumento(tipoDocumento.get())
                .usuario(usuario.get())
                .estado("A")
                .build();
        this.movAlmacenRepository.save(movAlmacen);
        return;
    }

    @Override
    public void modificar(Long id, MovAlmacenRequest t) {
        this.busca(id);
        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findById(t.getUsuario())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de usuario no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoDocumento> tipoDocumento = Optional.of(this.documentoRepository.findById(t.getTipoDocumento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo documento no encontrado...", HttpStatus.NOT_FOUND)));


        MovAlmacen movAlmacen = MovAlmacen.builder()
                .id(this.busca(id).getId())
                .tipoMovimiento(t.getTipoMovimiento())
                .fechaMovimiento(LocalDateTime.now())
                .obsevacionMovimiento(t.getObsevacionMovimiento())
                .tipoDocumento(tipoDocumento.get())
                .usuario(usuario.get())
                .build();
        this.movAlmacenRepository.save(movAlmacen);
        return;
    }
    @Transactional
    @Override
    public GenericResponse moverProducto(Long idMovimineto,MoverProductoRequest request) {
        Producto oProducto=new Producto();

        Optional<Producto> producto = Optional.ofNullable(this.productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de producto no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findById(request.getUsuario())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de usuario no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoDocumento> tipoDocumento = Optional.of(this.documentoRepository.findById(request.getTipoDocumento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo documento no encontrado...", HttpStatus.NOT_FOUND)));

        MovAlmacen oMovAlmacen= this.busca(idMovimineto);
        if (producto.get().getEstado().equalsIgnoreCase("A") && producto.get().getStock()>=request.getCantidadMovimiento()){
            oProducto.setStock(producto.get().getStock()-request.getCantidadMovimiento());

            oProducto.setId(producto.get().getId());
            oProducto.setNombre(producto.get().getNombre());
            oProducto.setPrecio(producto.get().getPrecio());
            oProducto.setFechaIngreso(producto.get().getFechaIngreso());
            oProducto.setFechaVencimiento(producto.get().getFechaVencimiento());
            oProducto.setEstado(producto.get().getEstado());
            oProducto.setProveedor(producto.get().getProveedor());
            oProducto.setCategoria(producto.get().getCategoria());
            oProducto.setUnidadMedida(producto.get().getUnidadMedida());
            this.productoRepository.save(oProducto);
            MovAlmacen movAlmacen=MovAlmacen.builder()
                    .id(oMovAlmacen.getId())
                    .tipoMovimiento(request.getTipoMovimiento())
                    .fechaMovimiento(LocalDateTime.now())
                    .obsevacionMovimiento(request.getObsevacionMovimiento())
                    .catidadMovimineto(oMovAlmacen.getCatidadMovimineto()+request.getCantidadMovimiento())
                    .usuario(usuario.get())
                    .tipoDocumento(tipoDocumento.get())
                    .estado(oMovAlmacen.getEstado())
                    .build();
            this.movAlmacenRepository.save(movAlmacen);
            return GenericResponse.builder()
                    .cod("0")
                    .mensage("Movimiento exitoso")
                    .build();
        }
        return GenericResponse.builder()
                .cod("-1")
                .mensage("Error de transaction :) ")
                .build();


    }

    @Override
    public boolean eliminar(Long id) {
        this.busca(id);
        this.movAlmacenRepository.deleteById(id);
        return true;
    }

    @Override
    public MovAlmacen busca(Long id) {
        return this.movAlmacenRepository.findById(id)
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de mov-almacen no encontrado...", HttpStatus.NOT_FOUND));

    }

    @Override
    public List<MovAlmacen> listar() {
        List<MovAlmacen> movAlmacens = this.movAlmacenRepository.findAll()
                .stream()
                .filter(m -> m.getEstado().equalsIgnoreCase("A"))
                .sorted(Comparator.comparing(MovAlmacen::getId).reversed())
                .collect(Collectors.toList());
        if (movAlmacens.isEmpty()) {
            throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, Constante.LISTA_VACIA, HttpStatus.NOT_FOUND);

        }
        return movAlmacens;
    }

    @Override
    public Page<MovAlmacen> listarPagina(Pageable page) {
        return null;
    }


}
