package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cate")
    private Long idCat;

    @Column(name = "nombre_cat")
    private String nombreCat;

    public Categoria() {
    }

    public Categoria(Long idCat, String nombreCat) {
        this.idCat = idCat;
        this.nombreCat = nombreCat;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }
}
