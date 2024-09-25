package com.smarfat.webapp.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dpi;
    private String telefono;

    @ManyToOne
    private Membresia membresia;

    @ManyToMany
    @JoinTable(name = "detalle_sedes",
        joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "sede_id", referencedColumnName = "id")
    )
    private List<Sede> sedes;
}
