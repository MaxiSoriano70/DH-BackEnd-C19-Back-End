package dh.backend.maxisoriano.ClinicaMVC.service.impl;



import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IPacienteRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return this.pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Integer id){
        return this.pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return this.pacienteRepository.findAll();
    }
    @Override
    public void actualizarPaciente(Paciente paciente) {
        this.pacienteRepository.save(paciente);
    }
    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = this.buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            this.pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("{\"message\": \"Paciente no eliminado\"}");
        }
    }

    @Override
    public List<Paciente> buscarPorDni(String dni) {
        return this.pacienteRepository.buscarPorDni(dni);
    }

    @Override
    public List<Paciente> buscarPorProvincia(String provincia) {
        return this.pacienteRepository.buscarPorProvinciaLike(provincia);
    }
}
