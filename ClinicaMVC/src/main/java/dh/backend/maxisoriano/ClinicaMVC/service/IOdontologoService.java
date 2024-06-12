package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(int id);
    List<Odontologo> buscarTodos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
}
