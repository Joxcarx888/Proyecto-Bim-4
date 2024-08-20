package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Entreno;
import com.smarfat.webapp.gym.repository.EntrenoRepository;

@Service
public class EntrenoService implements IEntrenoService{

    @Autowired
    EntrenoRepository entrenoRepository;
    ClienteService clienteService;
 
    @Override
    public List<Entreno> listarEntrenos() {
       return entrenoRepository.findAll();
    }
 
    @Override
    public Entreno buscarEntrenoPorId(Long id) {
        return entrenoRepository.findById(id).orElse(null);
    }
 
    @Override
    public Entreno guardarEntreno(Entreno entreno) {
        return entrenoRepository.save(entreno);
    }
 
    @Override
    public void eliminarEntreno(Entreno entreno) {
        entrenoRepository.delete(entreno);
    }

 
}
