package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.Repository.InstructorRepository;
import com.smarfat.webapp.gym.model.Instructor;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor buscarInstructorPorId(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarInstructor(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    @Override
    public Instructor guardarInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    

    @Override
    public List<Instructor> listarInstructor() {
        return instructorRepository.findAll();
    }

    
}
