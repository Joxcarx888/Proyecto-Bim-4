package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarClientes();

    public Cliente buscarCliente(Long id);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

    public Boolean limiteSedes(Cliente cliente);

    public Boolean verificarDpiDuplicado(Cliente cliente);
}
