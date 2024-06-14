package dh.backend.maxisoriano.ClinicaMVC.service;


import dh.backend.maxisoriano.ClinicaMVC.Dto.request.TurnoRequestDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.TurnoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) throws BadRequestException;
    TurnoResponseDto buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);
    void eliminarTurno(Integer id) throws ResourceNotFoundException;
    // HQL
    List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
}
