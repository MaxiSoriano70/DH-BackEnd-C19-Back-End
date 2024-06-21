package dh.backend.maxisoriano.ClinicaMVC.service.impl;


import dh.backend.maxisoriano.ClinicaMVC.Dto.request.TurnoRequestDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.OdontologoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.PacienteResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.TurnoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.entity.Turno;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IOdontologoRepository;
import dh.backend.maxisoriano.ClinicaMVC.repository.IPacienteRepository;
import dh.backend.maxisoriano.ClinicaMVC.repository.ITurnoRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private IPacienteRepository pacienteRepository;
    private IOdontologoRepository odontologoRepository;
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(paciente.isEmpty() && odontologo.isEmpty()){
            LOGGER.info("PACIENTE Y ODONTOLOGO NO ENCONTRADO");
            throw new BadRequestException ("{\"message\": \"paciente y odontologo no encontrado.\"}");
        }
        else if(paciente.isEmpty()){
            LOGGER.info("ODONTOLOGO NO ENCONTRADO");
            throw new BadRequestException ("{\"message\": \"paciente no encontrado.\"}");
        }
        else if(odontologo.isEmpty()){
            LOGGER.info("PACIENTE NO ENCONTRADO");
            throw new BadRequestException ("{\"message\": \"odontologo no encontrado.\"}");
        }else {
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = turnoRepository.save(turnoARegistrar);
            turnoADevolver = mapToResponseDto(turnoGuardado);
            LOGGER.info("TURNO PERSISTIDO: "+ turnoADevolver);
            return turnoADevolver;
        }
    }
    @Override
    public TurnoResponseDto buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isEmpty()){
            LOGGER.info("TURNO NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
        Turno turnoEncontrado = turnoOptional.get();
        TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
        LOGGER.info("TURNO ENCONTRADO: "+ turnoADevolver);
        return turnoADevolver;
    }
    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for(Turno turno: turnos){
            turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }
        LOGGER.info("TREAR TODOS LOS TURNOS");
        return turnosADevolver;
    }
    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Optional<Turno> turno = turnoRepository.findById(id);
        Turno turnoAModificar = new Turno();
        if(paciente.isPresent() && odontologo.isPresent() && turno.isPresent()){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            LOGGER.info("TURNO MODIFICADO");
            turnoRepository.save(turnoAModificar);
        }else {
            LOGGER.info("TURNO NO ENCONTRADO");
        }
    }
    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        TurnoResponseDto turnoResponseDto = this.buscarPorId(id);
        if(turnoResponseDto !=null) {
            LOGGER.info("TURNO ELIMINADO");
            turnoRepository.deleteById(id);
        }
        else{
            LOGGER.info("TURNO NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }
    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDto> listadoARetornar = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: listadoTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }
        LOGGER.info("TREAR TODOS POR FECHAS");
        return listadoARetornar;
    }
    // metodo que mapea turno en turnoResponseDto
    private TurnoResponseDto mapToResponseDto(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
