package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "det_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_det_ven")
    private Long idDetVen;

    // FK 1: Relación Muchos-a-Uno con Venta (El detalle pertenece a una venta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ven", nullable = false)
    private Venta venta;

    // FK 2: Relación Muchos-a-Uno con Deposito (De dónde sale el producto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_depo", nullable = false)
    private Deposito deposito;

    // FK 3: Relación Muchos-a-Uno con Producto (Qué producto se vendió)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pro", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Integer precioUnitario;

    @Column(name = "subtotal")
    private Integer subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetVen, Venta venta, Deposito deposito, Producto producto, Integer cantidad, Integer precioUnitario, Integer subtotal) {
        this.idDetVen = idDetVen;
        this.venta = venta;
        this.deposito = deposito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public Long getIdDetVen() {
        return idDetVen;
    }

    public void setIdDetVen(Long idDetVen) {
        this.idDetVen = idDetVen;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
