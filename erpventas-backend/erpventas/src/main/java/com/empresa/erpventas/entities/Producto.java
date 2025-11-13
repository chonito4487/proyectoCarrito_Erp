package com.empresa.erpventas.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro")
    private Long idPro;

    @Column(name = "nombre_pro")
    private String nombrePro;

    @Column(name = "cod_prod")
    private String codProd;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_pro")
    private String imagenPro;

    @Column(name = "precio_compra")
    private Integer precioCompra;

    @Column(name = "precio_venta")
    private Integer precioVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cate", nullable = false)
    private Categoria categoria;

    @Column(name = "estado_compra")
    private Boolean estadoCompra;

    @Column(name = "estado_pro")
    private Boolean estadoPro;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();

    public Producto() {
    }

    public Producto(Long idPro, String nombrePro, String codProd, String descripcion, String imagenPro, Integer precioCompra,
                    Integer precioVenta, Categoria categoria, Boolean estadoCompra, Boolean estadoPro, List<Stock> stocks) {
        this.idPro = idPro;
        this.nombrePro = nombrePro;
        this.codProd = codProd;
        this.descripcion = descripcion;
        this.imagenPro = imagenPro;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.estadoCompra = estadoCompra;
        this.estadoPro = estadoPro;
        this.stocks = stocks;
    }

    public Long getIdPro() {
        return idPro;
    }

    public void setIdPro(Long idPro) {
        this.idPro = idPro;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPro() {
        return imagenPro;
    }

    public void setImagenPro(String imagenPro) {
        this.imagenPro = imagenPro;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(Boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public Boolean getEstadoPro() {
        return estadoPro;
    }

    public void setEstadoPro(Boolean estadoPro) {
        this.estadoPro = estadoPro;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
