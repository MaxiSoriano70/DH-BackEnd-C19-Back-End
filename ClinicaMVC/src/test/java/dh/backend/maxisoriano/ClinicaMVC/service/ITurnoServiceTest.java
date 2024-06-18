package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.Dto.request.TurnoRequestDto;
import dh.backend.maxisoriano.ClinicaMVC.Dto.response.TurnoResponseDto;
import dh.backend.maxisoriano.ClinicaMVC.entity.Domicilio;
import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.entity.Turno;
import dh.backend.maxisoriano.ClinicaMVC.service.impl.OdontologoService;
import dh.backend.maxisoriano.ClinicaMVC.service.impl.PacienteService;
import dh.backend.maxisoriano.ClinicaMVC.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ITurnoServiceTest {
    public static Logger LOGGER = LoggerFactory.getLogger(ITurnoServiceTest.class);
    @Autowired

    private TurnoService turnoService;
  @Autowired
  private PacienteService pacienteService;
  @Autowired
  private OdontologoService odontologoService;
  private TurnoRequestDto turnoRequestDto;
  private Paciente paciente;
  private Odontologo odontologo;


    @BeforeEach
    void SetUp(){
        paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("San Pedro");
        domicilio.setProvincia("Jujuy");
        paciente.setDomicilio(domicilio);

        odontologo= new Odontologo();
        odontologo.setNombre("Maximiliano");
        odontologo.setApellido("Chavez");
        odontologo.setMatricula(76767);

        turnoRequestDto= new TurnoRequestDto();
        turnoRequestDto.setOdontologo_id(1);
        turnoRequestDto.setPaciente_id(2);
        turnoRequestDto.setFecha("24-08-2024");
    }
    @Test
    @DisplayName("Testear que un turno fue guardado")
    void testTurnoGuardado(){
        TurnoResponseDto turnoGuardadoEnLaBd = turnoService.registrar(turnoRequestDto);
        assertNotNull(turnoGuardadoEnLaBd);
    }
}