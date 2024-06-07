package dh.backend.maxisoriano.ClinicaMVC.controller;

import dh.backend.maxisoriano.ClinicaMVC.model.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    public IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);
        if(pacienteARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteARetornar);
        }

    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("Paciente actualizado.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente eliminado.");
    }
}

