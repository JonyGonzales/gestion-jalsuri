package com.idat.gestionjalsuri.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.gestionjalsuri.model.entity.Empleado;
import com.idat.gestionjalsuri.service.IEmpleadoService;
import com.idat.gestionjalsuri.util.Constante;

@RestController
@RequestMapping(Constante.URLPREFIJO + Constante.URLSUBFIJOEMPLEADO)
@CrossOrigin(origins = {"http://192.168.3.25:4200","http://localhost:4200","https://jalsuriweb.000webhostapp.com"})
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	// Metodo para Listar Empleados
		@GetMapping
		public ResponseEntity<List<Empleado>> listar() {

			List<Empleado> empleado = this.empleadoService.listar();

			if (empleado.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(empleado);

		}

		// Metodo para Crear Empelados
		@PostMapping
		public ResponseEntity<Empleado> agregar(@RequestBody Empleado empleado) {
			empleado.setEstado("Activo");
			Empleado empleados= this.empleadoService.registrar(empleado);

			if (empleados != null) {
				return ResponseEntity.created(URI.create("/" + empleado)).build();
			}

			return ResponseEntity.notFound().build();
		}

		// Metodo para buscar por ID
		@GetMapping("/{id}")
		public ResponseEntity<Empleado> buscar(@PathVariable("id") Long id) {
			Empleado emple = empleadoService.busca(id);
			if (emple == null) {
				emple = new Empleado();
			}
			return new ResponseEntity<Empleado>(emple, HttpStatus.OK);
		}

		// Metodo para Actualizar por ID
		@PutMapping("/{id}")
		public ResponseEntity<Empleado> actualizarEmpleadoxId(@PathVariable Long id, @RequestBody Empleado detalleEmpleado) {
			Empleado empleado = empleadoService.busca(id);
			
			empleado.setNombre(detalleEmpleado.getNombre());
			empleado.setArea(detalleEmpleado.getArea());
			empleado.setEstado("Activo");
			
			Empleado empleadoActualizado = empleadoService.modificar(empleado);
			return ResponseEntity.ok(empleadoActualizado);

		}

		@PutMapping("/deshabilitar/{id}")
		public ResponseEntity<Empleado> deshabilitarEmpleadoxId(@PathVariable Long id, @RequestBody Empleado detalleEmpleado) {
			Empleado empleado = empleadoService.busca(id);
			empleado.setEstado(detalleEmpleado.getEstado());

			Empleado empleadoActualizado = empleadoService.modificar(empleado);
			return ResponseEntity.ok(empleadoActualizado);

		}

		// Metodo que sirve para eliminar un item
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable("id") Long id) {
			empleadoService.eliminar(id);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar", Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
		}

}
