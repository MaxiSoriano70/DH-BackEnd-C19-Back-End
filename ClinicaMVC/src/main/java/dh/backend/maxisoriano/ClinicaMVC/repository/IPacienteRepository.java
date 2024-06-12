package dh.backend.maxisoriano.ClinicaMVC.repository;

import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
