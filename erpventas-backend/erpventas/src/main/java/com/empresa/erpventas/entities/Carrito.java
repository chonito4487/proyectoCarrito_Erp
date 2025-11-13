package com.empresa.erpventas.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @ManyToOne
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_carri")
    private LocalDate fechaCarri;

    @Column(name = "hora_carri")
    private LocalTime horaCarri;

    @Column(name = "forma_pago_carri")
    private String formaPagoCarri;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCarrito> detalles;

    public Carrito() {
    }

    public Carrito(Long idCarrito, Cliente cliente, LocalDate fechaCarri, LocalTime horaCarri, String formaPagoCarri, List<DetalleCarrito> detalles) {
        this.idCarrito = idCarrito;
        this.cliente = cliente;
        this.fechaCarri = fechaCarri;
        this.horaCarri = horaCarri;
        this.formaPagoCarri = formaPagoCarri;
        this.detalles = detalles;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCarri() {
        return fechaCarri;
    }

    public void setFechaCarri(LocalDate fechaCarri) {
        this.fechaCarri = fechaCarri;
    }

    public LocalTime getHoraCarri() {
        return horaCarri;
    }

    public void setHoraCarri(LocalTime horaCarri) {
        this.horaCarri = horaCarri;
    }

    public String getFormaPagoCarri() {
        return formaPagoCarri;
    }

    public void setFormaPagoCarri(String formaPagoCarri) {
        this.formaPagoCarri = formaPagoCarri;
    }

    public List<DetalleCarrito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarrito> detalles) {
        this.detalles = detalles;
    }
}
