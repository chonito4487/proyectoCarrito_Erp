package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

List<Producto> listarProducto();
Optional<Producto> buscarPorId(Long id);
Producto guardarProducto(Producto producto);
Optional<Producto> eliminarProducto(Long id);

}
