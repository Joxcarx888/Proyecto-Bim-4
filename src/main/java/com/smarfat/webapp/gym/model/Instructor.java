package com.smarfat.webapp.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Instructor")
public class Instructor {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreInstructor;
    private String telefonoInstructor;
    private Double sueldoInstructor;
    private String especialidadInstructor;
    
      
    
}