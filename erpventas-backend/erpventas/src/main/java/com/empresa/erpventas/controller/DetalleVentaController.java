package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Deposito;
import com.empresa.erpventas.entities.DetalleVenta;
import com.empresa.erpventas.entities.Producto;
import com.empresa.erpventas.entities.Venta;
import com.empresa.erpventas.service.DepositoService;
import com.empresa.erpventas.service.DetalleVentaService;
import com.empresa.erpventas.service.ProductoService;
import com.empresa.erpventas.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detallesventa")
public class DetalleVentaController {
    private final DetalleVentaService detVentaService;
    private final VentaService ventaService;
    private final DepositoService depositoService;
    private final ProductoService productoService;

    public DetalleVentaController(DetalleVentaService detVentaService, VentaService ventaService, DepositoService depositoService, ProductoService productoService) {
        this.detVentaService = detVentaService;
        this.ventaService = ventaService;
        this.depositoService = depositoService;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listarDetallesVenta() {
        return ResponseEntity.ok(detVentaService.listarDetallesVenta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscarPorId(@PathVariable Long id) {
        Optional<DetalleVenta> detVentaOpt = detVentaService.buscarPorId(id);
        if (detVentaOpt.isPresent()) {
            return ResponseEntity.ok(detVentaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta) {

        if (detalleVenta.getVenta() == null || detalleVenta.getVenta().getIdVenta() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long idVen = detalleVenta.getVenta().getIdVenta();
        Optional<Venta> ventaOpt = ventaService.buascarProId(idVen);

        if (ventaOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        detalleVenta.setVenta(ventaOpt.get());

        if (detalleVenta.getDeposito() == null || detalleVenta.getDeposito().getIdDepo() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long idDepo = detalleVenta.getDeposito().getIdDepo();
        Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);

        if (depositoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        detalleVenta.setDeposito(depositoOpt.get());

        if (detalleVenta.getProducto() == null || detalleVenta.getProducto().getIdPro() == null) {
            return ResponseEntity.badRequest().build();
        }
        Long idPro = detalleVenta.getProducto().getIdPro();
        Optional<Producto> productoOpt = productoService.buscarPorId(idPro);

        if (productoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        detalleVenta.setProducto(productoOpt.get());

        DetalleVenta detalleVentaGuardado = detVentaService.guardarDetalleVenta(detalleVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> editarDetalleVenta(@RequestBody DetalleVenta detalleVenta, @PathVariable Long id) {

        Optional<DetalleVenta> detVentaOpt = detVentaService.buscarPorId(id);
        if (detVentaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DetalleVenta detalleVentaDb = detVentaOpt.get();

        // --- 1. ACTUALIZACIÓN DE VENTA (Opcional en PUT) ---
        if (detalleVenta.getVenta() != null && detalleVenta.getVenta().getIdVenta() != null) {
            Long idVen = detalleVenta.getVenta().getIdVenta();
            Optional<Venta> ventaOpt = ventaService.buascarProId(idVen);

            if (ventaOpt.isPresent()) {
                detalleVentaDb.setVenta(ventaOpt.get());
            } else {
                return ResponseEntity.badRequest().build(); // Venta inválida
            }
        }

        if (detalleVenta.getDeposito() != null && detalleVenta.getDeposito().getIdDepo() != null) {
            Long idDepo = detalleVenta.getDeposito().getIdDepo();
            Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);

            if (depositoOpt.isPresent()) {
                detalleVentaDb.setDeposito(depositoOpt.get());
            } else {
                return ResponseEntity.badRequest().build(); // Depósito inválido
            }
        }

        if (detalleVenta.getProducto() != null && detalleVenta.getProducto().getIdPro() != null) {
            Long idPro = detalleVenta.getProducto().getIdPro();
            Optional<Producto> productoOpt = productoService.buscarPorId(idPro);

            if (productoOpt.isPresent()) {
                detalleVentaDb.setProducto(productoOpt.get());
            } else {
                return ResponseEntity.badRequest().build(); // Producto inválido
            }
        }

        if (detalleVenta.getCantidad() != null) {
            detalleVentaDb.setCantidad(detalleVenta.getCantidad());
        }
        if (detalleVenta.getPrecioUnitario() != null) {
            detalleVentaDb.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        }
        if (detalleVenta.getSubtotal() != null) {
            detalleVentaDb.setSubtotal(detalleVenta.getSubtotal());
        }

        DetalleVenta detalleVentaActualizada = detVentaService.guardarDetalleVenta(detalleVentaDb);
        return ResponseEntity.status(HttpStatus.OK).body(detalleVentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleVenta> eliminarDetalleVenta(@PathVariable Long id) {
        Optional<DetalleVenta> detVentaOpt = detVentaService.eliminarDetalleVenta(id);

        if (detVentaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(detVentaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
