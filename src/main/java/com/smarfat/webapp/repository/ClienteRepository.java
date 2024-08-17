package com.smarfat.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarfat.webapp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
