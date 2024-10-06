package com.samu.sistema.controller;

import com.samu.sistema.model.Paciente;
import com.samu.sistema.repository.InMemoryPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private InMemoryPacienteRepository repository;

    @GetMapping
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = repository.findAll();
        model.addAttribute("pacientes", pacientes);
        return "paciente-lista";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente-form";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        repository.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/pacientes";
    }
}
