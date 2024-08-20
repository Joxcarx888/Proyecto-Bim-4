package com.smarfat.webapp.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarfat.webapp.gym.model.Instructor;
import com.smarfat.webapp.gym.service.InstructorService;

@Controller
@RestController
@RequestMapping("")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @GetMapping("/instructores")
    public List<Instructor> listarInstructores(){
        return instructorService.listarInstructor();
    }

    @GetMapping("/instructor")
    public ResponseEntity<Instructor> buscarInstructorPorId(@RequestParam Long id){
        try {
            Instructor instructor = instructorService.buscarInstructorPorId(id);
            return ResponseEntity.ok(instructor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/instructor")
    public ResponseEntity<Map<String, String>> guardarInstructor(@RequestBody Instructor instructor) {
        Map<String, String> response = new HashMap<>();
        try {
            instructorService.guardarInstructor(instructor);
            response.put("message", "Se ha creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/instructor")
    public ResponseEntity<Map<String, String>> editarInstructor(@RequestParam Long id, @RequestBody Instructor instructorNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Instructor instructor = instructorService.buscarInstructorPorId(id);
            instructor.setNombreInstructor(instructorNuevo.getNombreInstructor());
            instructor.setEspecialidadInstructor(instructorNuevo.getEspecialidadInstructor());
            instructor.setTelefonoInstructor(instructorNuevo.getTelefonoInstructor());
            instructor.setSueldoInstructor(instructorNuevo.getSueldoInstructor());
            instructorService.guardarInstructor(instructor);
            response.put("message", "El instructor se ha modificado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el instructor");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/instructor")
    public ResponseEntity<Map<String, String>> eliminarInstructor(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Instructor instructor = instructorService.buscarInstructorPorId(id);
            instructorService.eliminarInstructor(instructor);
            response.put("message", "El instructor se ha eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "El instructor no se ha eliminado con éxito");
            return ResponseEntity.badRequest().body(response);
        }
    }
}