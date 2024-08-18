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

import com.smarfat.webapp.gym.model.Maquina;
import com.smarfat.webapp.gym.service.MaquinaService;

@Controller
@RestController
@RequestMapping("")
public class MaquinaController {

    @Autowired
    MaquinaService maquinaService;

    @GetMapping("/maquinas")
    public List<Maquina> listarMaquinas(){
        return maquinaService.listarMaquinas();
    }

    @GetMapping("/maquina")
    public ResponseEntity<Maquina> buscarMaquinaPorId(@RequestParam Long id){
        try {
            Maquina maquina = maquinaService.buscarMaquinaPorId(id);
            return ResponseEntity.ok(maquina);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/maquina")
    public ResponseEntity<Map<String,String >> agregarMaquina(@RequestBody Maquina maquina) {
        Map<String,String> response = new HashMap<>();
        try {
            maquinaService.guardarMaquina(maquina);
            response.put("message","Maquina agregada con exito" );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado la maquina" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/maquina")
    public ResponseEntity<Map<String, String>> editarMaquina(@RequestParam Long id, @RequestBody Maquina maquinaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Maquina maquina = maquinaService.buscarMaquinaPorId(id);
            maquina.setMarca(maquinaNueva.getMarca());
            maquinaService.guardarMaquina(maquina);
            response.put("message", "La maquina se ha modificado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar la maquina");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/maquina")
    public ResponseEntity<Map<String, String>> eliminarMaquina(@RequestParam Long id){
        Map <String, String> response = new HashMap<>();
        try {
            Maquina maquina = maquinaService.buscarMaquinaPorId(id);
            maquinaService.eliminarMaquina(maquina);
            response.put("Message", "La maquina se eleiminó con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Message", "Error");
            response.put("err", "La maquina no existe o no se econtró");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
