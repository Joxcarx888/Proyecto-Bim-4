package com.smarfat.webapp.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarfat.webapp.gym.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsuario(String usuario);
}
