package dh.backend.maxisoriano.ClinicaMVC.service.impl;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IOdontologoRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return this.odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(int id) {
        return this.odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return this.odontologoRepository.findAll();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        this.odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = this.buscarPorId(id);
        if (odontologoOptional.isPresent()) {
            this.odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }

    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }
}
