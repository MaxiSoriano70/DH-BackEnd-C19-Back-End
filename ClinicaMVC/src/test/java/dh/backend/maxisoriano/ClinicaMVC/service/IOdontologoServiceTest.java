package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.BadRequestException;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IOdontologoServiceTest {
    public static Logger LOOGER = LoggerFactory.getLogger(IOdontologoService.class);
    @Autowired
    private OdontologoService odontologoService;
   private Odontologo odontologo;
   @BeforeEach
    void setUp(){
       odontologo = new Odontologo();
       odontologo.setNombre("Maximiliano");
       odontologo.setApellido("Chavez");
       odontologo.setMatricula(76767);

       }

    @Test
    @DisplayName("Testear que el odontologo fue guardado")
    void testOdontologoGuardado() throws BadRequestException {
        Odontologo odontologoDesdelaBd= odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoDesdelaBd);
   }

    @Test
    @DisplayName(" Testear que el odontologo fue editado")
    void testOdontoloEditado() throws ResourceNotFoundException {
       odontologoService.actualizarOdontologo(odontologo);
    }


}


