package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Instructor;

public interface IInstructorService {

    public List<Instructor> listarInstructor();
    public Instructor guardarInstructor(Instructor instructor);
    public Instructor buscarInstructorPorId(Long id);
    public void eliminarInstructor(Instructor instructor);      

    
}