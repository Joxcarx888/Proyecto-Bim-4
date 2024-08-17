package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Membresia;
import com.smarfat.webapp.gym.repository.MembresiaRepository;

@Service
public class MembresiaService implements IMembresiaService {
    @Autowired
    MembresiaRepository membreciaRepository;

    @Override
    public List<Membresia> listarMembresias() {
        return membreciaRepository.findAll();
    }

    @Override
    public Membresia guardarMembresia(Membresia membresia) {
        return membreciaRepository.save(membresia);
    }

    @Override
    public Membresia buscarMembresiaPorId(Long id) {
        return membreciaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarMembresia(Membresia membresia) {
        membreciaRepository.delete(membresia);
    }

}

