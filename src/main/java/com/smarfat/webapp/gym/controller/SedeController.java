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

import com.smarfat.webapp.gym.model.Sede;
import com.smarfat.webapp.gym.service.SedeService;

@Controller
@RestController
@RequestMapping("")
public class SedeController {
    @Autowired
    SedeService sedeService;

    @GetMapping("/sedes")
    public ResponseEntity<List<Sede>> listarSedes() {
        try {
            return ResponseEntity.ok(sedeService.listarSedes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/sede")
    public ResponseEntity<Sede> buscarSedePorId(@RequestParam long id) {
        try {
            return ResponseEntity.ok(sedeService.buscarSedePorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/sede")
    public ResponseEntity<Map<String, String>> agregarSede(@RequestBody Sede sede) {
        Map<String, String> response = new HashMap<>();

        try {
            if(!sedeService.verificarSedeDuplicada(sede)){
                sedeService.guardarSede(sede);
                response.put("message", "Se agregado con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message" ,"error" );
                response.put("err" ,"La direccion ya se utilizo" );
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha agregado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/sede")
    public ResponseEntity<Map<String, String>> editarSede(@RequestParam Long id, @RequestBody Sede sedeNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            Sede sede = sedeService.buscarSedePorId(id);
            sede.setDireccion(sedeNueva.getDireccion());

            sedeService.guardarSede(sede);
            response.put("message", "Se ha modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha modificado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/sede")
    public ResponseEntity<Map<String, String>> eliminarSede(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Sede sede = sedeService.buscarSedePorId(id);

            sedeService.eliminarSede(sede);
            response.put("message", "Se ha eliminado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "No se ha eliminado con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
