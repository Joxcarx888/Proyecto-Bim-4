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

import com.smarfat.webapp.gym.model.Inscripcion;
import com.smarfat.webapp.gym.service.InscripcionService;

@Controller
@RestController
@RequestMapping("")
public class InscripcionController {

    @Autowired
    InscripcionService inscripcionService;

    @GetMapping("/inscripciones")
    public ResponseEntity<List<Inscripcion>> listarInscripciones(){
        try {
            return ResponseEntity.ok(inscripcionService.listarInscripciones());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/inscripcion")
    public ResponseEntity<Inscripcion> buscarInscripcionPorId(@RequestParam long id){
        try {
            return ResponseEntity.ok(inscripcionService.buscarInscripcionPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/inscripcion")
    public ResponseEntity<Map<String,String>> agregarInscripcion(@RequestBody Inscripcion inscripcion) {
        Map<String,String> response = new HashMap<>();

        try {
            inscripcionService.guardarInscripcion(inscripcion);
            response.put("message", "Se ha creado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha agregado la inscripción");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/inscripcion")
    public ResponseEntity<Map<String, String>> editarInscripcion(@RequestParam Long id, @RequestBody Inscripcion inscripcionNuevo) {
        Map<String,String> response = new HashMap<>();
        try {
            Inscripcion inscripcion = inscripcionService.buscarInscripcionPorId(id);
            inscripcion.setCliente(inscripcionNuevo.getCliente());
            inscripcion.setSede(inscripcionNuevo.getSede());
            inscripcionService.guardarInscripcion(inscripcion);
            response.put("message", "Se ha modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha modificado con éxito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/inscripcion")
    public ResponseEntity<Map<String, String>> eliminarInscripcion(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Inscripcion inscripcion = inscripcionService.buscarInscripcionPorId(id);
            inscripcionService.eliminarInscripcion(inscripcion);
            response.put("message", "Se ha eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha eliminado con éxito");
            return ResponseEntity.badRequest().body(response);
        }
    }

}


