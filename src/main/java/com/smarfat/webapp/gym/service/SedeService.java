package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Sede;
import com.smarfat.webapp.gym.repository.SedeRepository;

@Service
public class SedeService implements ISedeService {
    @Autowired
    SedeRepository sedeRepository;

    @Override
    public List<Sede> listarSedes() {
        return sedeRepository.findAll();
    }

    @Override
    public Sede guardarSede(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public Sede buscarSedePorId(Long id) {
        return sedeRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarSede(Sede sede) {
        sedeRepository.delete(sede);
    }

}