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

import com.smarfat.webapp.gym.model.Membresia;
import com.smarfat.webapp.gym.service.MembresiaService;

@Controller
@RestController
@RequestMapping("")
public class MembresiaController {
    @Autowired
    MembresiaService membresiaService;

    @GetMapping("/membresias")
    public ResponseEntity<List<Membresia>> listarMembresias() {
        try {
            return ResponseEntity.ok(membresiaService.listarMembresias());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/membresia")
    public ResponseEntity <Membresia> buscarMembresiaPorId(@RequestParam long id) {
        try {
            return ResponseEntity.ok(membresiaService.buscarMembresiaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @PostMapping("/membresia")
    public ResponseEntity<Map<String,String>> agregarMembresia(@RequestBody Membresia membresia) {
        Map<String,String> response = new HashMap<>();

        try {
            membresiaService.guardarMembresia(membresia);
            response.put("message","Se agregado con exito" );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/membresia")
    public ResponseEntity <Map<String, String>> editarMembresia(@RequestParam Long id, @RequestBody Membresia membresiaNueva) {
        Map<String,String> response = new HashMap<>();
        try {
            Membresia membresia = membresiaService.buscarMembresiaPorId(id);
            membresia.setDuracion(membresiaNueva.getDuracion());
            membresia.setVigencia(membresiaNueva.getVigencia());

            membresiaService.guardarMembresia  (membresia);
            response.put("message", "Se he modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha modificado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/membresia")
    public ResponseEntity<Map<String, String>> eliminarMembresia(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Membresia membresia = membresiaService.buscarMembresiaPorId(id);

            membresiaService.eliminarMembresia(membresia);
            response.put("message", "Se ha elimnado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha eliminado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
}
