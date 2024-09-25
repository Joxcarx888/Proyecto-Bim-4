package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Sesion;

public interface ISesionService {

    public List<Sesion> listarSesiones();

    public Sesion guardarSesion(Sesion sesion);

    public Sesion buscarSesionPorId(Long id);

    public void eliminarSesion(Sesion sesion);
}
