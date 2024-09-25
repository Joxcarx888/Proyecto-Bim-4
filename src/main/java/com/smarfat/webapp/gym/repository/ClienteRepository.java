package com.smarfat.webapp.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarfat.webapp.gym.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
