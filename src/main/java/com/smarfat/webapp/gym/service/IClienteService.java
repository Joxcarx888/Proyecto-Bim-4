package com.smarfat.webapp.service;

import java.util.List;

import com.smarfat.webapp.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarClientes();

    public Cliente buscarCliente(Long id);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
