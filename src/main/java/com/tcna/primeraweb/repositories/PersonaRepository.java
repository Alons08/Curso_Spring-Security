package com.tcna.primeraweb.repositories;

import com.tcna.primeraweb.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    //INGLES
    //Aqui puedo poner más metodos a parte del CRUD [1]

}


