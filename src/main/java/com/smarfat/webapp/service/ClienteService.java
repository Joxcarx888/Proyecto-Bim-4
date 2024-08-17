package com.smarfat.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarfat.webapp.model.Cliente;
import com.smarfat.webapp.repository.ClienteRepository;

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

}
