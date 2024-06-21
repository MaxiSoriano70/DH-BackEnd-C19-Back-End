package dh.backend.maxisoriano.ClinicaMVC.controller;


import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    public IPacienteService pacienteService;
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.registrarPaciente(paciente));
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente.get());
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("{\"message\": \"paciente modificado\"}");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("{\"message\": \"Paciente eliminado\"}");
    }
    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<Paciente>> buscarPorDni(@PathVariable String dni){
        List<Paciente> listaPacientes = pacienteService.buscarPorDni(dni);
        if(listaPacientes.size()>0){
            return  ResponseEntity.ok(listaPacientes);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<Paciente>> buscarPorProvincia(@PathVariable String provincia){
        List<Paciente> listaPacientes = pacienteService.buscarPorProvincia(provincia);
        if(listaPacientes.size()>0){
            return  ResponseEntity.ok(listaPacientes);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

