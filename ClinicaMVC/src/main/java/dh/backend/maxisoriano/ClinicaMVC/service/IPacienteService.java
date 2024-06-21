package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPacienteService {
    Paciente registrarPaciente(Paciente paciente) throws BadRequestException;
    Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException;
    List<Paciente> buscarTodos();
    void actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id) throws ResourceNotFoundException;
    List<Paciente> buscarPorDni(String dni);
    List<Paciente> buscarPorProvincia(String provincia);
}
