package com.idat.gestionjalsuri.service.impl;

import com.idat.gestionjalsuri.exception.ExceptionService;
import com.idat.gestionjalsuri.model.entity.*;
import com.idat.gestionjalsuri.model.request.MovAlmacenRequest;
import com.idat.gestionjalsuri.model.request.MoverProductoRequest;
import com.idat.gestionjalsuri.model.response.GenericResponse;
import com.idat.gestionjalsuri.model.response.MovAlmacenResponse;
import com.idat.gestionjalsuri.repository.*;
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

    @Autowired
    private DetalleMovimintoRepository detalleMovimintoRepository;


    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;



    @Override
    public void modificar(Long id, MovAlmacenRequest t) {
        this.busca(id);
        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findById(t.getUsuario())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de usuario no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoDocumento> tipoDocumento = Optional.of(this.documentoRepository.findById(t.getTipoDocumento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo documento no encontrado...", HttpStatus.NOT_FOUND)));
        Optional<TipoMovimiento> tipoMovimiento = Optional.of(this.tipoMovimientoRepository.findById(t.getTipoMovimiento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo movimiento no encontrado...", HttpStatus.NOT_FOUND)));


        MovAlmacen movAlmacen = MovAlmacen.builder()
                .id(this.busca(id).getId())
                .catidadMovimineto(t.getCatidadMovimineto())
                .codTransaccion(this.busca(id).getCodTransaccion())
                .fechaMovimiento(LocalDateTime.now())
                .obsevacionMovimiento(t.getObsevacionMovimiento())
                .tipoDocumento(tipoDocumento.get())
                .usuario(usuario.get())
                .tipoMovimiento(tipoMovimiento.get())
                .estado("A")
                .build();
        this.movAlmacenRepository.save(movAlmacen);
        return;
    }

    @Transactional
    @Override
    public GenericResponse registrarMovimiento(MoverProductoRequest request) {

        Optional<Producto> producto = Optional.ofNullable(this.productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de producto no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findById(request.getUsuario())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de usuario no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoDocumento> tipoDocumento = Optional.of(this.documentoRepository.findById(request.getTipoDocumento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo documento no encontrado...", HttpStatus.NOT_FOUND)));

        Optional<TipoMovimiento> tipoMovimiento = Optional.of(this.tipoMovimientoRepository.findById(request.getTipoMovimiento())
                .orElseThrow(() -> new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, "Id de tipo movimiento no encontrado...", HttpStatus.NOT_FOUND)));


        if (tipoMovimiento.get().getNombreTipo().equalsIgnoreCase("salida") &&
                producto.get().getEstado().equalsIgnoreCase("A")) {
            if (request.getCantidadMovimiento() > producto.get().getStock()) {
                return GenericResponse.builder()
                        .cod("-1")
                        .mensage("Producto insuficiente :) ")
                        .build();
            }
            producto.get().setStock(producto.get().getStock() - request.getCantidadMovimiento());
            this.productoRepository.save(producto.get());
            MovAlmacen movAlmacen = MovAlmacen.builder()
                    .codTransaccion(producto.get().getCodTransaccion())
                    .fechaMovimiento(LocalDateTime.now())
                    .obsevacionMovimiento(request.getObsevacionMovimiento())
                    .catidadMovimineto(request.getCantidadMovimiento())
                    .usuario(usuario.get())
                    .tipoDocumento(tipoDocumento.get())
                    .tipoMovimiento(tipoMovimiento.get())
                    .estado("A")
                    .build();

            MovAlmacen almacen= this.movAlmacenRepository.save(movAlmacen);

            this.detalleMovimintoRepository.save(getDetalleMovimiento(producto,almacen));
            return GenericResponse.builder()
                    .cod("0")
                    .mensage("Movimiento exitoso")
                    .build();
        } else if (tipoMovimiento.get().getNombreTipo().equalsIgnoreCase("retorno")) {

            if (request.getCantidadMovimiento() > producto.get().getStock()) {
                return GenericResponse.builder()
                        .cod("-1")
                        .mensage("Producto insuficiente :) ")
                        .build();
            }

            producto.get().setStock(producto.get().getStock() + request.getCantidadMovimiento());
            this.productoRepository.save(producto.get());
            MovAlmacen movAlmacen = MovAlmacen.builder()
                    .codTransaccion(producto.get().getCodTransaccion())
                    .fechaMovimiento(LocalDateTime.now())
                    .obsevacionMovimiento(request.getObsevacionMovimiento())
                    .catidadMovimineto(request.getCantidadMovimiento())
                    .usuario(usuario.get())
                    .tipoDocumento(tipoDocumento.get())
                    .tipoMovimiento(tipoMovimiento.get())
                    .estado("A")
                    .build();
            MovAlmacen almacen= this.movAlmacenRepository.save(movAlmacen);

            if (request.getCantidadMovimiento() > this.getTotalSalida().stream()
                    .mapToInt(m->m.getCatidadMovimineto())
                    .sum()){
                return GenericResponse.builder()
                        .cod("-1")
                        .mensage("No tiene suficiente stock para devolver :) ")
                        .build();

            }
            this.detalleMovimintoRepository.save(getDetalleMovimiento(producto,almacen));
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

    private DetalleMovimiento getDetalleMovimiento(Optional<Producto> producto, MovAlmacen almacen) {
        return DetalleMovimiento.builder()
                .obsevacionMovimiento(almacen.getObsevacionMovimiento())
                .idMovAlmacen(almacen.getId())
                .idProducto(producto.get().getId())
                .nombreProducto(producto.get().getNombre())
                .unidadMedida(producto.get().getUnidadMedida().getNombre())
                .estado("A")
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
    public MovAlmacenResponse listar(String tipoMovimiento) {
        List<MovAlmacen> movAlmacens = null;

        if (tipoMovimiento.isBlank()) {
            movAlmacens= this.movAlmacenRepository.findAll().stream()
                    .filter(m->m.getEstado()!=null && m.getEstado().equalsIgnoreCase("A"))
                    .collect(Collectors.toList());
        }else{
            movAlmacens = this.movAlmacenRepository.findAll()
                    .stream()
                    .filter(m -> m.getEstado().equalsIgnoreCase("A")
                            && m.getTipoMovimiento().getNombreTipo()!=null
                            && m.getTipoMovimiento().getNombreTipo().equalsIgnoreCase(tipoMovimiento))
                    .sorted(Comparator.comparing(MovAlmacen::getId)
                            .reversed())
                    .collect(Collectors.toList());


        }

        if (movAlmacens.isEmpty()) {
            throw new ExceptionService(Constante.CODIGO_ID_NO_ENCONTRADO, Constante.LISTA_VACIA, HttpStatus.NOT_FOUND);
        }

        return MovAlmacenResponse.builder()
                .movAlmacens(movAlmacens)
                .numeroLista(movAlmacens.size())
                .totalProducto(movAlmacens.stream().mapToLong(m->m.getCatidadMovimineto()).sum())
                .build();


    }

    @Override
    public Page<MovAlmacen> listarPagina(Pageable page) {
        return null;
    }
    public List<MovAlmacen>getTotalSalida(){

        return this.movAlmacenRepository.findAll()
                .stream()
                .filter(m->m.getTipoMovimiento().getNombreTipo()!=null &&
                        m.getEstado()!=null &&
                        m.getTipoMovimiento().getNombreTipo().equalsIgnoreCase("salida") &&
                        m.getEstado().equalsIgnoreCase("A"))

                .collect(Collectors.toList());
    }


}
