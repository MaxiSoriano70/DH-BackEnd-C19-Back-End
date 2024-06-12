package dh.backend.maxisoriano.ClinicaMVC.controller;


import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/vista")
public class VistaController {
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;

    public VistaController(IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscarPaciente")
    public String buscarPacientePorId(Model model, @RequestParam Integer id){
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);
        if(pacienteOptional.isPresent()){
            model.addAttribute("especialidad", "Paciente");
            model.addAttribute("nombre", pacienteOptional.get().getNombre());
            model.addAttribute("apellido", pacienteOptional.get().getApellido());
            return "index";
        }else {
            return null;
        }

    }

    @GetMapping("/buscarOdontologo")
    public String buscarOdontologoPorId(Model model, @RequestParam Integer id){
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(id);
        if(odontologoOptional.isPresent()){
            model.addAttribute("especialidad", "odontologo");
            model.addAttribute("nombre", odontologoOptional.get().getNombre());
            model.addAttribute("apellido", odontologoOptional.get().getApellido());
            return "index";
        }else {
            return null;
        }
    }
}
