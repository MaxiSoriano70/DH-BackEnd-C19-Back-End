package dh.backend.maxisoriano.ClinicaMVC.service.impl;



import dh.backend.maxisoriano.ClinicaMVC.dao.IDao;
import dh.backend.maxisoriano.ClinicaMVC.model.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo crearOdontologo(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }


    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return this.odontologoIDao.registrar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return this.odontologoIDao.buscarPorId(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return this.odontologoIDao.buscarTodos();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        this.odontologoIDao.actualizar(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        this.odontologoIDao.eliminar(id);
    }
}
