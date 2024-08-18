package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Inscripcion;

public interface IInscripcionService {
    public List<Inscripcion> listarInscripciones();

    public Inscripcion buscarInscripcionPorId(Long id);

    public Inscripcion guardarInscripcion(Inscripcion inscripcion);

    public void eliminarInscripcion(Inscripcion inscripcion);
}


