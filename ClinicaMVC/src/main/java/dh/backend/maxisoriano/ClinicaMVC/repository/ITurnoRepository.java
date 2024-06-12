package dh.backend.maxisoriano.ClinicaMVC.repository;

import dh.backend.maxisoriano.ClinicaMVC.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
