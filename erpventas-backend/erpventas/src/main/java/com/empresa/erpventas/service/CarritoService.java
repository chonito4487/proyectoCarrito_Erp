package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    List<Carrito> listarCarritos();
    Optional<Carrito> buscarPorId(Long id);
    Carrito guardarCarrito(Carrito carrito);
    Optional<Carrito> eliminarCarrito(Long id);

    List<Carrito> listarCarritosPorCliente(Long idCliente);
    void vaciarCarrito(Long idCarrito);


}
