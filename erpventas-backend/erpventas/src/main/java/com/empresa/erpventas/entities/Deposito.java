package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "depositos")
public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depo")
    private Long idDepo;

    @Column(name = "nombre_depo", nullable = false, length = 100)
    private String nombreDepo;


    public Deposito() {
    }

    public Deposito(Long idDepo, String nombreDepo) {
        this.idDepo = idDepo;
        this.nombreDepo = nombreDepo;
    }

    public Long getIdDepo() {
        return idDepo;
    }

    public void setIdDepo(Long idDepo) {
        this.idDepo = idDepo;
    }

    public String getNombreDepo() {
        return nombreDepo;
    }

    public void setNombreDepo(String nombreDepo) {
        this.nombreDepo = nombreDepo;
    }
}
