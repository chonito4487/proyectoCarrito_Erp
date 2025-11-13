package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.DetalleVenta;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleVentaService {
    List<DetalleVenta> listarDetallesVenta();
    Optional<DetalleVenta> buscarPorId(Long id);
    DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta);
    Optional<DetalleVenta> eliminarDetalleVenta(Long id);
}
