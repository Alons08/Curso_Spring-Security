package com.tcna.primeraweb;

import com.tcna.primeraweb.models.Persona;
import com.tcna.primeraweb.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PrimeraWebSpringbootApplication implements CommandLineRunner {

	@Autowired
	public PersonaRepository personaRepository; //Para usarlo bien abajo

	public static void main(String[] args) {
		SpringApplication.run(PrimeraWebSpringbootApplication.class, args);
	}

	//METODO DE "CommanLineRunner" PARA VER SI SE GUARDA BIEN EN LA BD
	@Override
	public void run(String... args) throws Exception {
		/*personaRepository.save(new Persona(null,"Alonso", 18));
		personaRepository.save(new Persona(null,"Maria", 20));
		personaRepository.save(new Persona(null,"Juana", 24));*/

		//para imprimir en la consola
		System.out.println("\nNumero de persona en la tabla: "+personaRepository.count());
		List<Persona> personas = personaRepository.findAll();
		personas.forEach(p -> System.out.println("Nombre: " + p.getNombre()));

	}
}

