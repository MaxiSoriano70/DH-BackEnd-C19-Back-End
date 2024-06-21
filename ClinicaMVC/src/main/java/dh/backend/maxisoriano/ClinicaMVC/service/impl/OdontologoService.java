package dh.backend.maxisoriano.ClinicaMVC.service.impl;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IOdontologoRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoRegistrado = this.odontologoRepository.save(odontologo);
        if(odontologoRegistrado == null){
            LOGGER.info("ODONTOLOGO NO PERSISTIDO");
            throw new BadRequestException("{\"message\": \"odontologo no registrado\"}");
        }
        LOGGER.info("ODONTOLOGO PERSISTIDO: " + odontologoRegistrado);
        return odontologoRegistrado;
    }
    @Override
    public Optional<Odontologo> buscarPorId(int id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isEmpty()){
            LOGGER.info("ODONTOLOGO NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }
        LOGGER.info("ODONTOLOGO ENCONTRADO: "+odontologoOptional.get());
        return odontologoOptional;
    }
    @Override
    public List<Odontologo> buscarTodos() {
        LOGGER.info("TRAER TODOS LOS ODONTOLOGOS");
        return this.odontologoRepository.findAll();
    }
    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        LOGGER.info("ODONTOLOGO ACTUALIZADO: "+odontologo);
        this.odontologoRepository.save(odontologo);
    }
    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = this.buscarPorId(id);
        if (odontologoOptional.isPresent()) {
            LOGGER.info("ODONTOLOGO ELIMINADO: "+ odontologoOptional.get());
            this.odontologoRepository.deleteById(id);
        } else {
            LOGGER.info("ODONTOLOGO NO ENCONTRADO");
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }
    }
    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        LOGGER.info("TRAER TODOS LOS ODONTOLOGOS POR APELLIDO");
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        LOGGER.info("TRAER TODOS LOS ODONTOLOGOS POR NOMBRE");
        return odontologoRepository.findByNombreLike(nombre);
    }
}
