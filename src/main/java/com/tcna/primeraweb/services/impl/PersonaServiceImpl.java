package com.tcna.primeraweb.services.impl;

import com.tcna.primeraweb.models.Persona;
import com.tcna.primeraweb.repositories.PersonaRepository;
import com.tcna.primeraweb.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

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
