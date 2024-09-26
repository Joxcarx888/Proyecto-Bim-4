package com.smarfat.webapp.gym.service;

import com.smarfat.webapp.gym.model.Entreno;
import com.smarfat.webapp.gym.model.Maquina;

import java.util.List;

public interface IEntrenoService {
    public List<Entreno> listarEntrenos();

    public Entreno buscarEntrenoPorId(Long id);

    public Entreno guardarEntreno(Entreno entreno);

    public void eliminarEntreno(Entreno entreno);

    public Boolean limiteMaquinas(Entreno entreno);

    public Boolean limiteClientes(Entreno entreno);

    public Boolean verificarMembresia(Entreno entreno);


}