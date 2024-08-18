package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Inscripcion;
import com.smarfat.webapp.gym.repository.InscripcionRepository;

@Service
public class InscripcionService implements IInscripcionService {

    @Autowired
    InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion buscarInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    @Override
    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public void eliminarInscripcion(Inscripcion inscripcion) {
        inscripcionRepository.delete(inscripcion);
    }
}

