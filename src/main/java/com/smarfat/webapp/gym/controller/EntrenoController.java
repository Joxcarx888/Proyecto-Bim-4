package com.smarfat.webapp.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarfat.webapp.gym.model.Entreno;
import com.smarfat.webapp.gym.service.EntrenoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RestController
@RequestMapping("")


public class EntrenoController {
    @Autowired
    EntrenoService entrenoService;

    @GetMapping("/entrenos")
    public ResponseEntity<?> listarEntrenos() {
        Map<String,String> response = new HashMap<>();

        try {
            return ResponseEntity.ok(entrenoService.listarEntrenos());
        } catch (Exception e) {
            response.put("Ayuda", "Xd");
            response.put("err","No se encontro una lista");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/entreno")
    public ResponseEntity <Entreno> buscarEntrenoPorId(@RequestParam long id) {
        try {
            return ResponseEntity.ok(entrenoService.buscarEntrenoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/entreno")
    public ResponseEntity <Map<String, String>> agregarEntreno(@RequestBody Entreno entreno){
        Map<String,String> response = new HashMap<>();

        try {
            if(!entrenoService.verificarMembresia(entreno)){
                if(!entrenoService.limiteMaquinas(entreno)){
                    if(!entrenoService.limiteClientes(entreno)){
                        entrenoService.guardarEntreno(entreno);
                        response.put("message", "Se ha creado con exito");
                        return ResponseEntity.ok(response);
                    }else{  
                        response.put("message" ,"error" );
                        response.put("err" ,"Solo se puede registrar hasta 5 clientes" );
                        return ResponseEntity.badRequest().body(response);
                    }
            
                    
                }else{
                    response.put("message" ,"error" );
                    response.put("err" ,"Solo se puede registrar hasta 5 maquinas" );
                    return ResponseEntity.badRequest().body(response);
                }   
            }else{
                response.put("message" ,"error" );
                response.put("err" ,"Alguna membrecia esta vencida verifica el cliente" );
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/entreno")
    public ResponseEntity <Map<String, String>> editarEntreno(@RequestParam Long id, @RequestBody Entreno entrenoNuevo) {
        Map<String,String> response = new HashMap<>();
        try {
            Entreno entreno = entrenoService.buscarEntrenoPorId(id);

            entreno.setInstructor(entrenoNuevo.getInstructor());
            entreno.setSesion(entrenoNuevo.getSesion());
            entreno.setClientes(entrenoNuevo.getClientes());
            entreno.setMaquinas(entrenoNuevo.getMaquinas());
            entrenoService.guardarEntreno(entreno);
            response.put("message", "Se he modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha modificado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/entreno")
    public ResponseEntity<Map<String, String>> eliminarEntreno(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Entreno entreno = entrenoService.buscarEntrenoPorId(id);

            entrenoService.eliminarEntreno(entreno);
            response.put("message", "Se ha elimnado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha eliminado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }
    
}
