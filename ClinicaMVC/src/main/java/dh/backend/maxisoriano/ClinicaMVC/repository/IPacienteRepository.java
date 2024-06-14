package dh.backend.maxisoriano.ClinicaMVC.repository;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("Select p from Paciente p where p.dni = ?1")
    List<Paciente> buscarPorDni(String dni);

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.domicilio.provincia) LIKE LOWER(CONCAT('%', :provincia, '%'))")
    List<Paciente> buscarPorProvinciaLike(@Param("provincia") String provincia);
}
