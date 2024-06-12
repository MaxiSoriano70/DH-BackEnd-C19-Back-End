package dh.backend.maxisoriano.ClinicaMVC.service.impl;


import dh.backend.maxisoriano.ClinicaMVC.Dto.request.TurnoRequestDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.OdontologoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.PacienteResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.TurnoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.entity.Turno;
import dh.backend.maxisoriano.ClinicaMVC.repository.IOdontologoRepository;
import dh.backend.maxisoriano.ClinicaMVC.repository.IPacienteRepository;
import dh.backend.maxisoriano.ClinicaMVC.repository.ITurnoRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
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
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(pacienteOptional.isPresent() && odontologoOptional.isPresent()){
            turnoARegistrar.setOdontologo(odontologoOptional.get());
            turnoARegistrar.setPaciente(pacienteOptional.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = turnoRepository.save(turnoARegistrar);

            turnoADevolver = mapToResponseDto(turnoGuardado);

            // armar el turno a devolver
//            PacienteResponseDto pacienteResponseDto = new PacienteResponseDto();
//            pacienteResponseDto.setId(turnoGuardado.getPaciente().getId());
//            pacienteResponseDto.setApellido(turnoGuardado.getPaciente().getApellido());
//            pacienteResponseDto.setNombre(turnoGuardado.getPaciente().getNombre());
//            pacienteResponseDto.setDni(turnoGuardado.getPaciente().getDni());
//
//            OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto();
//            odontologoResponseDto.setId(turnoGuardado.getOdontologo().getId());
//            odontologoResponseDto.setApellido(turnoGuardado.getOdontologo().getApellido());
//            odontologoResponseDto.setNombre(turnoGuardado.getOdontologo().getNombre());
//            odontologoResponseDto.setNroMatricula(turnoGuardado.getOdontologo().getNroMatricula());
//
//            turnoADevolver = new TurnoResponseDto();
//            turnoADevolver.setId(turnoGuardado.getId());
//            turnoADevolver.setOdontologo(odontologoResponseDto);
//            turnoADevolver.setPaciente(pacienteResponseDto);
//            turnoADevolver.setFecha(turnoGuardado.getFecha().toString());
        }
        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()){
            Turno turnoEncontrado = turnoOptional.get();
            TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
            return turnoADevolver;
        }
        return null;
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
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        Turno turnoAModificar = new Turno();
        if(pacienteOptional.isPresent() && odontologoOptional.isPresent() && turnoOptional.isPresent()){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologoOptional.get());
            turnoAModificar.setPaciente(pacienteOptional.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoRepository.save(turnoAModificar);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }

    // metodo que mapea turno en turnoResponseDto
    private TurnoResponseDto mapToResponseDto(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
