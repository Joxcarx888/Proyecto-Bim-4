package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Sesion;
import com.smarfat.webapp.gym.repository.SesionRepository;

@Service
public class SesionService implements ISesionService {
    @Autowired
    SesionRepository sesionRepository;

    @Override
    public List<Sesion> listarSesiones() {
        return sesionRepository.findAll();
    }

    @Override
    public Sesion guardarSesion(Sesion sesion) {
        return sesionRepository.save(sesion);
    }

    @Override
    public Sesion buscarSesionPorId(Long id) {
        return sesionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarSesion(Sesion sesion) {
        sesionRepository.delete(sesion);
    }

}
