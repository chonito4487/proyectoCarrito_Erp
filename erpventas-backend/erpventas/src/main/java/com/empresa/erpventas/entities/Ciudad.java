package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciu")
    private Long idCiu;

    @Column(name="nombre_ciu")
    private String nombreCiu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dep", nullable = false)
    private Departamento departamento;

    public Ciudad() {
    }

    public Ciudad(Long idCiu, String nombreCiu, Departamento departamento) {
        this.idCiu = idCiu;
        this.nombreCiu = nombreCiu;
        this.departamento = departamento;
    }

    public Long getIdCiu() {
        return idCiu;
    }

    public void setIdCiu(Long idCiu) {
        this.idCiu = idCiu;
    }

    public String getNombreCiu() {
        return nombreCiu;
    }

    public void setNombreCiu(String nombreCiu) {
        this.nombreCiu = nombreCiu;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
