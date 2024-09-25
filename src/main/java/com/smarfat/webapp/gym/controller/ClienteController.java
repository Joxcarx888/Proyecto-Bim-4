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

import com.smarfat.webapp.gym.model.Cliente;
import com.smarfat.webapp.gym.service.ClienteService;

@Controller
@RestController
@RequestMapping("")

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(){
        try {
            return ResponseEntity.ok(clienteService.listarClientes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarClientePorId(@RequestParam long id){
        try {
            return ResponseEntity.ok(clienteService.buscarCliente(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String,String>> agregarCliente(@RequestBody Cliente cliente) {
        Map<String,String> response = new HashMap<>();

        try {
            if(!clienteService.limiteSedes(cliente)){
                if(!clienteService.verificarDpiDuplicado(cliente)){
                    clienteService.guardarCliente(cliente);
                    response.put("message", "Se ha creado con exito");
                    return ResponseEntity.ok(response);
                }else{
                    response.put("message" ,"error" );
                    response.put("err" ,"El Dpi ya se ha utilizado verifica el error" );
                    return ResponseEntity.badRequest().body(response);
                }
                
            }else{
                response.put("message" ,"error" );
                response.put("err" ,"Solo se puede registrar hasta 5 sedes" );
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado el Cliente" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity <Map<String, String>> editarCliente(@RequestParam Long id, @RequestBody Cliente clienteNuevo) {
        Map<String,String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setDpi(clienteNuevo.getDpi());
            cliente.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(clienteNuevo);
            response.put("message", "Se he modificado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha modificado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarCliente(id);

            clienteService.eliminarCliente(cliente);
            response.put("message", "Se ha elimnado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha eliminado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

}
