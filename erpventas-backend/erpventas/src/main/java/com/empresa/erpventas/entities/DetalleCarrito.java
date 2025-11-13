package com.empresa.erpventas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "det_carrito")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_det_carrito")
    private Long idDetCarrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_depo", nullable = false)
    private Deposito deposito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pro", nullable = false)
    private Producto producto;

    private Integer cantidad;
    @Column(name = "precio_unitario")
    private Integer precioUnitario;

    private Integer subtotal;

    public DetalleCarrito() {
    }

    public DetalleCarrito(Carrito carrito, Deposito deposito, Producto producto, Integer cantidad, Integer precioUnitario, Integer subtotal) {
        this.carrito = carrito;
        this.deposito = deposito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public Long getIdDetCarrito() {
        return idDetCarrito;
    }

    public void setIdDetCarrito(Long idDetCarrito) {
        this.idDetCarrito = idDetCarrito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
