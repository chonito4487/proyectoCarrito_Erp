package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dep")
    private Long idDepto;

    @Column(name = "nombre_dep")
    private String nombreDepto;

    public Departamento() {
    }

    public Departamento(Long idDepto, String nombreDepto) {
        this.idDepto = idDepto;
        this.nombreDepto = nombreDepto;
    }

    public Long getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Long idDepto) {
        this.idDepto = idDepto;
    }

    public String getNombreDepto() {
        return nombreDepto;
    }

    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }
}
