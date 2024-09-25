package com.smarfat.webapp.gym.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Entrenos")
public class Entreno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Instructor instructor;

    @ManyToOne
    private Sesion sesion;

    @ManyToMany
    @JoinTable(name = "detalle_entreno_cliente",
        joinColumns = @JoinColumn(name = "entreno_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    )
    private List<Cliente> clientes;

    @ManyToMany
    @JoinTable(name = "detalle_entreno_maquina",
        joinColumns = @JoinColumn(name = "entreno_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "maquina_id", referencedColumnName = "id")
    )
    private List<Maquina> maquinas;
}


