package dh.backend.maxisoriano.ClinicaMVC.service.impl;



import dh.backend.maxisoriano.ClinicaMVC.dao.IDao;
import dh.backend.maxisoriano.ClinicaMVC.model.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return pacienteIDao.registrar(paciente);
    }

    public Paciente buscarPorId(Integer id){
        return pacienteIDao.buscarPorId(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }
    @Override
    public void actualizarPaciente(Paciente paciente) {
        this.pacienteIDao.actualizar(paciente);
    }
    @Override
    public void eliminarPaciente(Integer id) {
        this.pacienteIDao.eliminar(id);
    }
}
