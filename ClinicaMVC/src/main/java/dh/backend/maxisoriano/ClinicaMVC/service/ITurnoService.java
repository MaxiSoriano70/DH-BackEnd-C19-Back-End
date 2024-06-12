package dh.backend.maxisoriano.ClinicaMVC.service;


import dh.backend.maxisoriano.ClinicaMVC.Dto.request.TurnoRequestDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.TurnoResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto);
    TurnoResponseDto buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);
    void eliminarTurno(Integer id);
}
