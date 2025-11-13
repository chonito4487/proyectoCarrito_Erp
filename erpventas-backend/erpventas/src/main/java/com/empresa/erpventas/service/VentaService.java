package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> listarVentas();
    Optional<Venta> buascarProId(Long id);
    Venta guardarVenta(Venta venta);
    Optional<Venta> eliminarVenta(Long id);
}
