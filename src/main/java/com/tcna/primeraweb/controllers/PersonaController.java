package com.tcna.primeraweb.controllers;

import com.tcna.primeraweb.models.Persona;
import com.tcna.primeraweb.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService service;

    @GetMapping("")
    public String listarPersonas(Model model){
        List<Persona> personas = service.listarTodos();
        model.addAttribute("listaPersonas", personas);
                                                 //key, value
        return "listar";
    }

    @GetMapping("/nuevo") //get porque va a mostrar una vista
    public String mostrarFormularioDeNuevaPersona(Model model){
        model.addAttribute("persona", new Persona());
        model.addAttribute("accion","/personas/nuevo");
        return "formulario";
    }

    @PostMapping("/nuevo")
    public String guardarNuevaPersona(@ModelAttribute Persona per){
        service.crear(per);
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}") //get porque va a mostrar una vista
    public String mostrarFormularioDeEditarPersona(@PathVariable Long id, Model model){
        Persona person = service.obtenerPorId(id);
        model.addAttribute("persona", person);
        model.addAttribute("accion", "/personas/editar/"+id);
        return "formulario";
    }


    @PostMapping("/editar/{id}")  //put no usamos xk se usa solo en los servicios REST
    public String editarPersona(@PathVariable Long id, @ModelAttribute Persona per){
        service.actualizar(id, per);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        service.eliminar(id);
        return ("redirect:/personas");
    }

}
