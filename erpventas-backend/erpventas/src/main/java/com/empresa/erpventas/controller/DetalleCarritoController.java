package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Carrito;
import com.empresa.erpventas.entities.Deposito;
import com.empresa.erpventas.entities.DetalleCarrito;
import com.empresa.erpventas.entities.Producto;
import com.empresa.erpventas.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detallescarrito")
public class DetalleCarritoController {

    private final DetalleCarritoService detCarritoService;
    private final CarritoService carritoService;
    private final ProductoService productoService;
    private final DepositoService depositoService;
    private final StockService stockService;

    public DetalleCarritoController(DetalleCarritoService detCarritoService, CarritoService carritoService, ProductoService productoService, DepositoService depositoService, StockService stockService) {
        this.detCarritoService = detCarritoService;
        this.carritoService = carritoService;
        this.productoService = productoService;
        this.depositoService = depositoService;
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleCarrito>> listarDetallesCarrito() {
        return ResponseEntity.ok(detCarritoService.listarDetallesCarrito());
    }

    @GetMapping("/carrito/{idCarrito}")
    public ResponseEntity<List<DetalleCarrito>> listarPorIdCarrito(@PathVariable Long idCarrito) {
        return ResponseEntity.ok(detCarritoService.listarPorIdCarrito(idCarrito));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCarrito> buscarPorId(@PathVariable Long id) {
        return detCarritoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleCarrito> guardarDetalleCarrito(@RequestBody DetalleCarrito detalleCarrito) {

        // --- 1. validacion carrito (FK) ---
        if (detalleCarrito.getCarrito() == null || detalleCarrito.getCarrito().getIdCarrito() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long idCarrito = detalleCarrito.getCarrito().getIdCarrito();
        Optional<Carrito> carritoOpt = carritoService.buscarPorId(idCarrito);
        if (carritoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        detalleCarrito.setCarrito(carritoOpt.get());


        // --- 2. validacion producto (FK) ---
        if (detalleCarrito.getProducto() == null || detalleCarrito.getProducto().getIdPro() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long idPro = detalleCarrito.getProducto().getIdPro();
        Optional<Producto> productoOpt = productoService.buscarPorId(idPro);
        if (productoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        detalleCarrito.setProducto(productoOpt.get());


        // --- 3. validacion deposito (FK) ---
        if (detalleCarrito.getDeposito() != null && detalleCarrito.getDeposito().getIdDepo() != null) {
            Long idDepo = detalleCarrito.getDeposito().getIdDepo();
            Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);
            if (depositoOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            detalleCarrito.setDeposito(depositoOpt.get());
        }

        // Guardar
        DetalleCarrito detalleGuardado = detCarritoService.guardarDetalleCarrito(detalleCarrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCarrito> editarDetalleCarrito(@RequestBody DetalleCarrito detalleCarrito, @PathVariable Long id) {

        Optional<DetalleCarrito> detCarritoOpt = detCarritoService.buscarPorId(id);
        if (detCarritoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DetalleCarrito detalleDb = detCarritoOpt.get();

        // --- 1. act carrito (FK) ---
        if (detalleCarrito.getCarrito() != null && detalleCarrito.getCarrito().getIdCarrito() != null) {
            Long idCarrito = detalleCarrito.getCarrito().getIdCarrito();
            Optional<Carrito> carritoOpt = carritoService.buscarPorId(idCarrito);
            if (carritoOpt.isPresent()) {
                detalleDb.setCarrito(carritoOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        // --- 2.act producto (FK) ---
        if (detalleCarrito.getProducto() != null && detalleCarrito.getProducto().getIdPro() != null) {
            Long idPro = detalleCarrito.getProducto().getIdPro();
            Optional<Producto> productoOpt = productoService.buscarPorId(idPro);
            if (productoOpt.isPresent()) {
                detalleDb.setProducto(productoOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        // --- 3. act deposito (FK) ---
        if (detalleCarrito.getDeposito() != null && detalleCarrito.getDeposito().getIdDepo() != null) {
            Long idDepo = detalleCarrito.getDeposito().getIdDepo();
            Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);
            if (depositoOpt.isPresent()) {
                detalleDb.setDeposito(depositoOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        if (detalleCarrito.getCantidad() != null) {
            detalleDb.setCantidad(detalleCarrito.getCantidad());
        }
        if (detalleCarrito.getPrecioUnitario() != null) {
            detalleDb.setPrecioUnitario(detalleCarrito.getPrecioUnitario());
        }
        if (detalleCarrito.getSubtotal() != null) {
            detalleDb.setSubtotal(detalleCarrito.getSubtotal());
        }

        // Guardar
        DetalleCarrito detalleActualizado = detCarritoService.guardarDetalleCarrito(detalleDb);
        return ResponseEntity.status(HttpStatus.OK).body(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleCarrito> eliminarDetalleCarrito(@PathVariable Long id) {
        Optional<DetalleCarrito> detalleOpt = detCarritoService.eliminarDetalleCarrito(id);

        if (detalleOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(detalleOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/carrito/{idCarrito}")
    public ResponseEntity<String> eliminarDetallesPorCarritoId(@PathVariable Long idCarrito) {

        Optional<Carrito> carritoOpt = carritoService.buscarPorId(idCarrito);
        if (carritoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carrito no encontrado.");
        }

        List<DetalleCarrito> detalles = detCarritoService.listarPorIdCarrito(idCarrito);
        if (detalles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El carrito no tiene detalles para eliminar.");
        }

        detCarritoService.eliminarPorCarritoId(idCarrito);
        return ResponseEntity.ok("Detalles del carrito eliminados correctamente.");
    }

    @PostMapping
    public ResponseEntity<DetalleCarrito> agregarDetalleCarrito(@RequestBody DetalleCarrito detalle) {

        Long idPro = detalle.getProducto().getIdPro();
        Long idDepo = detalle.getDeposito().getIdDepo();
        int cantidad = detalle.getCantidad();

        // Validamos stock antes de agregar
        if (!stockService.hayStockDisponible(idPro, idDepo, cantidad)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Podrías también devolver un mensaje de error personalizado
        }

        DetalleCarrito nuevo = detCarritoService.guardarDetalleCarrito(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }



}
