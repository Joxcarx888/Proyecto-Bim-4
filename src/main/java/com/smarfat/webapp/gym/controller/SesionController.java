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

import com.smarfat.webapp.gym.model.Sesion;
import com.smarfat.webapp.gym.service.SesionService;

@Controller
@RestController
@RequestMapping("")
public class SesionController {
    @Autowired
    SesionService sesionService;

    @GetMapping("/sesiones")
    public ResponseEntity<List<Sesion>> listarSesiones() {
        try {
            return ResponseEntity.ok(sesionService.listarSesiones());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/sesion")
    public ResponseEntity<Sesion> buscarSesionPorId(@RequestParam long id) {
        try {
            return ResponseEntity.ok(sesionService.buscarSesionPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/sesion")
    public ResponseEntity<Map<String, String>> agregarSesion(@RequestBody Sesion sesion) {
        Map<String, String> response = new HashMap<>();

        try {
            sesionService.guardarSesion(sesion);
            response.put("message", "Se agregado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha agregado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/sesion")
    public ResponseEntity<Map<String, String>> editarSesion(@RequestParam Long id, @RequestBody Sesion sesionNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            Sesion sesion = sesionService.buscarSesionPorId(id);
            sesion.setEspecialidad(sesionNueva.getEspecialidad());

            sesionService.guardarSesion(sesion);
            response.put("message", "Se he modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha modificado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/sesion")
    public ResponseEntity<Map<String, String>> eliminarSesion(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Sesion sesion = sesionService.buscarSesionPorId(id);

            sesionService.eliminarSesion(sesion);
            response.put("message", "Se ha elimnado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha eliminado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

