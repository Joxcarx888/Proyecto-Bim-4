package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Maquina;

public interface IMaquinaService {

    public List<Maquina> listarMaquinas();
    
    public Maquina guardarMaquina(Maquina maquina);

    public Maquina buscarMaquinaPorId(Long id);
     
    public void eliminarMaquina(Maquina maquina);
}
