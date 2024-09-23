package com.smarfat.webapp.gym.service;

import java.util.List;

import com.smarfat.webapp.gym.model.Usuario;

public interface IUsuarioService{
    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuario(String usuario);

    public Usuario guardarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);

    
}
