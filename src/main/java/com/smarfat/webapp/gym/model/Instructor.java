package com.smarfat.webapp.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Instructores")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreInstructor;
    private String telefonoInstructor;
    private Double sueldoInstructor;
    private String especialidadInstructor;

    @Override
    public String toString() {
        return "Id: " + id + " | " + nombreInstructor + " " + especialidadInstructor;
    }

    public void setNombre(String text) {
        throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    }

    public void setNivelAcceso(String selectedItem) {
        throw new UnsupportedOperationException("Unimplemented method 'setNivelAcceso'");
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public String getNivelAcceso() {
        throw new UnsupportedOperationException("Unimplemented method 'getNivelAcceso'");
    }

    
      
    
}