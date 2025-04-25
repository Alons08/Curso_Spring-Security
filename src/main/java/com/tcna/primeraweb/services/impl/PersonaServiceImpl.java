package com.tcna.primeraweb.services.impl;

import com.tcna.primeraweb.models.Persona;
import com.tcna.primeraweb.repositories.PersonaRepository;
import com.tcna.primeraweb.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> listarTodos() {
        return personaRepository.findAll();
    }

    @Override
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public Persona crear(Persona objeto) {
        return personaRepository.save(objeto);
    }

    @Override
    public Persona actualizar(Long id, Persona objeto) {
        if (personaRepository.existsById(id)){
            return personaRepository.save(objeto);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        if (personaRepository.existsById(id)){
            personaRepository.deleteById(id);
        }
    }

}
