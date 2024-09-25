package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Membresia;

public interface IMembresiaService {
    public List<Membresia> listarMembresias();

    public Membresia guardarMembresia(Membresia membresia);

    public Membresia buscarMembresiaPorId(Long id);

    public void eliminarMembresia(Membresia membresia);
}
