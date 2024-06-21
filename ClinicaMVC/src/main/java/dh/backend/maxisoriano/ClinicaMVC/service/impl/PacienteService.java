package dh.backend.maxisoriano.ClinicaMVC.service.impl;



import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IPacienteRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        Paciente pacienteARetornar = this.pacienteRepository.save(paciente);
        if(pacienteARetornar == null){
            LOGGER.info("PACIENTE NO PERSISTIDO");
            throw new BadRequestException("{\"message\": \"paciente no registrado\"}");
        }
        LOGGER.info("PACIENTE PERSISTIDO: " + pacienteARetornar);
        return pacienteARetornar;
    }

    public Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isEmpty()){
            LOGGER.info("PACIENTE NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
        /*LOGGER.info("PACIENTE ENCONTRADO: " + pacienteOptional.get());*/
        return pacienteOptional;
    }

    public List<Paciente> buscarTodos(){
        LOGGER.info("TRAER TODOS LOS PACIENTES");
        return this.pacienteRepository.findAll();
    }
    /*CONSULTA NO FUNCIONA CUANDO LE AGREGO => throws ResourceNotFoundException EN IpacienteService
    * @Override
    public Paciente actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = this.buscarPorId(paciente.getId());
        if (pacienteOptional.isEmpty()) {
            LOGGER.info("PACIENTE NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
        return this.pacienteRepository.save(pacienteOptional.get());
    }
    * */
    @Override
    public void actualizarPaciente(Paciente paciente){
        LOGGER.info("PACIENTE ACTUALIZADO"+ paciente);
        this.pacienteRepository.save(paciente);
    }
    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = this.buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            LOGGER.info("PACIENTE ELIMINADO: "+pacienteOptional.get());
            this.pacienteRepository.deleteById(id);
        } else {
            LOGGER.info("PACIENTE NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"Paciente no eliminado\"}");
        }
    }

    @Override
    public List<Paciente> buscarPorDni(String dni)  {
        LOGGER.info("TRAER TODOS LOS PACIENTES POR DNI");
        return this.pacienteRepository.buscarPorDni(dni);
    }

    @Override
    public List<Paciente> buscarPorProvincia(String provincia) {
        LOGGER.info("TRAER TODOS LOS PACIENTES POR PROVINCIA");
        return this.pacienteRepository.buscarPorProvinciaLike(provincia);
    }
}
