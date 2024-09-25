package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Sede;

public interface ISedeService {

    public List<Sede> listarSedes();

    public Sede guardarSede(Sede sede);

    public Sede buscarSedePorId(Long id);

    public void eliminarSede(Sede sede);

    public Boolean verificarSedeDuplicada(Sede sede);
}