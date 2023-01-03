package com.idat.gestionjalsuri;

import com.idat.gestionjalsuri.model.entity.UnidadMedida;
import com.idat.gestionjalsuri.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class GestionJalsuriApplication implements CommandLineRunner {
	@Autowired
	private UnidadMedidaRepository medidaRepository;
	public static void main(String[] args) {
		SpringApplication.run(GestionJalsuriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<UnidadMedida>o=this.medidaRepository.findById(2l);
		System.out.println("##################: "+o.get());
	}
}
