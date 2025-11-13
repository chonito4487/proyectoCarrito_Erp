package com.empresa.erpventas.service;
import com.empresa.erpventas.entities.DetalleCarrito;

import java.util.List;
import java.util.Optional;

public interface DetalleCarritoService {
    List<DetalleCarrito> listarDetallesCarrito();
    Optional<DetalleCarrito> buscarPorId(Long id);
    DetalleCarrito guardarDetalleCarrito(DetalleCarrito detalleCarrito);
    Optional<DetalleCarrito> eliminarDetalleCarrito(Long id);
    List<DetalleCarrito> listarPorIdCarrito(Long idCarrito);
}
