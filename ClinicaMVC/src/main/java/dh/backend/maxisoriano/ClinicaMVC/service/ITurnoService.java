package dh.backend.maxisoriano.ClinicaMVC.service;


import dh.backend.maxisoriano.ClinicaMVC.model.Turno;

import java.util.List;

public interface ITurnoService {
    Turno registrar(Turno turno);

    Turno buscarPorId(Integer id);

    List<Turno> buscarTodos();
    void actualizarTurno(Turno turno);
    void eliminarTurno(Integer id);
}
