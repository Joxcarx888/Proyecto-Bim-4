package com.smarfat.webapp.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.gym.model.Cliente;
import com.smarfat.webapp.gym.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public Boolean limiteSedes(Cliente cliente){
        Boolean flag = Boolean.FALSE;

        if (cliente.getSedes().size() > 5) {
            flag = Boolean.TRUE;
        }

        return flag;
    }

    @Override
    public Boolean verificarDpiDuplicado(Cliente cliente){
        Boolean flag = Boolean.FALSE;
        List<Cliente> clientes = listarClientes();

        for (Cliente cl : clientes) {
            if(cl.getDpi().equals(cliente.getDpi()) && !cl.getId().equals(cliente.getId())){
                flag = Boolean.TRUE;
            }
        }

        return flag;
    }

    
    

}
