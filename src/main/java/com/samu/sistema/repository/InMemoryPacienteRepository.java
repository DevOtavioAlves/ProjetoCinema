package com.samu.sistema.repository;

import com.samu.sistema.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPacienteRepository {
    private List<Paciente> pacientes = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    // Retorna todos os pacientes
    public List<Paciente> findAll() {
        return pacientes;
    }

    // Salva um novo paciente ou atualiza um existente
    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(idGenerator.getAndIncrement());
        }
        pacientes.removeIf(p -> p.getId().equals(paciente.getId()));
        pacientes.add(paciente);
        return paciente;
    }

    // Retorna um paciente por ID, se existir
    public Optional<Paciente> findById(Long id) {
        return pacientes.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // Exclui um paciente pelo ID
    public void deleteById(Long id) {
        pacientes.removeIf(p -> p.getId().equals(id));
    }
}