package com.empresa.erpventas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli")
    private Long idCli;

    @Column(name = "nombre_cli")
    private String nombreCli;

    @Column(name = "apellido_cli")
    private String apellidoCli;

    @Column(name = "edad_cli")
    private Integer edadCli;

    @Column(name = "num_ci_ruc")
    private String ciRuc;

    @Column(name = "telefono_cli")
    private String telefonoCli;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciu", nullable = false)
    private Ciudad ciudad;

    @Column(name = "foto_cli")
    private String fotosCli;

    @Column(name = "fecha_naci")
    private LocalDate fechaNacCli;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private String rol; // CLIENTE o ADMIN

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "num_tarjeta")
    private String numTarjeta;

    @Column(name = "tipo_tarjeta")
    private String tipoTarjeta;

    public Cliente() {
    }

    public Cliente(Long idCli, String nombreCli, String apellidoCli, Integer edadCli, String ciRuc, String telefonoCli,
                   Ciudad ciudad, String fotosCli, LocalDate fechaNacCli, String username, String password, String rol,
                   Boolean estado, String numTarjeta, String tipoTarjeta) {
        this.idCli = idCli;
        this.nombreCli = nombreCli;
        this.apellidoCli = apellidoCli;
        this.edadCli = edadCli;
        this.ciRuc = ciRuc;
        this.telefonoCli = telefonoCli;
        this.ciudad = ciudad;
        this.fotosCli = fotosCli;
        this.fechaNacCli = fechaNacCli;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.numTarjeta = numTarjeta;
        this.tipoTarjeta = tipoTarjeta;
    }

    public Long getIdCli() {
        return idCli;
    }

    public void setIdCli(Long idCli) {
        this.idCli = idCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getApellidoCli() {
        return apellidoCli;
    }

    public void setApellidoCli(String apellidoCli) {
        this.apellidoCli = apellidoCli;
    }

    public Integer getEdadCli() {
        return edadCli;
    }

    public void setEdadCli(Integer edadCli) {
        this.edadCli = edadCli;
    }

    public String getCiRuc() {
        return ciRuc;
    }

    public void setCiRuc(String ciRuc) {
        this.ciRuc = ciRuc;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getFotosCli() {
        return fotosCli;
    }

    public void setFotosCli(String fotosCli) {
        this.fotosCli = fotosCli;
    }

    public LocalDate getFechaNacCli() {
        return fechaNacCli;
    }

    public void setFechaNacCli(LocalDate fechaNacCli) {
        this.fechaNacCli = fechaNacCli;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}
