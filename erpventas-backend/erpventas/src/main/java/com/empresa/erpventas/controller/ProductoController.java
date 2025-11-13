package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Categoria;
import com.empresa.erpventas.entities.Producto;
import com.empresa.erpventas.service.CategoriaService;
import com.empresa.erpventas.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService service, CategoriaService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(service.listarProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        Optional<Producto> productoOpt = service.buscarPorId(id);
        if (productoOpt.isPresent()) {
            return ResponseEntity.ok(productoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {

        if (producto.getCategoria() != null && producto.getCategoria().getIdCat() != null) {
            Long idCate = producto.getCategoria().getIdCat();
            Optional<Categoria> categoriaOpt = categoriaService.buscarPorId(idCate);

            if (categoriaOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            producto.setCategoria(categoriaOpt.get());
        }

        Producto productoGuardado = service.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@RequestBody Producto producto, @PathVariable Long id) {

        Optional<Producto> productoOpt = service.buscarPorId(id);
        if (productoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto productoDb = productoOpt.get();

        productoDb.setNombrePro(producto.getNombrePro());
        productoDb.setCodProd(producto.getCodProd());
        productoDb.setDescripcion(producto.getDescripcion());
        productoDb.setImagenPro(producto.getImagenPro());
        productoDb.setPrecioCompra(producto.getPrecioCompra());
        productoDb.setPrecioVenta(producto.getPrecioVenta());
        productoDb.setEstadoCompra(producto.getEstadoCompra());
        productoDb.setEstadoPro(producto.getEstadoPro());

        if (producto.getCategoria() != null && producto.getCategoria().getIdCat() != null) {
            Long idCategoria = producto.getCategoria().getIdCat();
            Optional<Categoria> categoriaOpt = categoriaService.buscarPorId(idCategoria);

            if (categoriaOpt.isPresent()) {

                productoDb.setCategoria(categoriaOpt.get());
            } else {

                return ResponseEntity.badRequest().build();
            }
        }

        Producto productoActualizado = service.guardarProducto(productoDb);
        return ResponseEntity.status(HttpStatus.OK).body(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> productoOpt = service.eliminarProducto(id);

        if (productoOpt.isPresent()) {
            Producto productoEliminar = productoOpt.get();
            return ResponseEntity.status(HttpStatus.OK).body(productoEliminar);
        }
        return ResponseEntity.notFound().build();
    }
}
