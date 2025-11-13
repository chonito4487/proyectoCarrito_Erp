package com.empresa.erpventas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ven")
    private Long idVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cli", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_ven")
    private LocalDate fechaVen;

    @Column(name = "hora_ven")
    private LocalTime horaVen;

    @Column(name = "forma_pago")
    private String formaPago;

    public Venta() {
    }

    public Venta(Long idVenta, Cliente cliente, LocalDate fechaVen, LocalTime horaVen, String formaPago) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVen = fechaVen;
        this.horaVen = horaVen;
        this.formaPago = formaPago;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaVen() {
        return fechaVen;
    }

    public void setFechaVen(LocalDate fechaVen) {
        this.fechaVen = fechaVen;
    }

    public LocalTime getHoraVen() {
        return horaVen;
    }

    public void setHoraVen(LocalTime horaVen) {
        this.horaVen = horaVen;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}
