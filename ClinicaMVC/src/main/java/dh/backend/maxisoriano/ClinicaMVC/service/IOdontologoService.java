package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.model.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.model.Paciente;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Odontologo buscarPorId(int id);
    List<Odontologo> buscarTodos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
}
