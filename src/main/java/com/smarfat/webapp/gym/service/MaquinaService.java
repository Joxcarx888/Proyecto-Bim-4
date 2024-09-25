package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Maquina;
import com.smarfat.webapp.gym.repository.MaquinaRepository;

@Service
public class MaquinaService implements IMaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    @Override
    public List<Maquina> listarMaquinas() {
        return maquinaRepository.findAll();
    }

    @Override
    public Maquina guardarMaquina(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    @Override
    public Maquina buscarMaquinaPorId(Long id) {
        return maquinaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarMaquina(Maquina maquina) {
        maquinaRepository.delete(maquina);
    }


    




}
