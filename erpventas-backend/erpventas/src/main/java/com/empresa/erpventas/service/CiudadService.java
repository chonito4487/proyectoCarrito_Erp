package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Ciudad;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    List<Ciudad> listarCiudad();
    Optional<Ciudad> buscarPorId(Long id);
    Ciudad guardarCiudad(Ciudad ciudad);
    Optional<Ciudad> eliminarCiudad(Long id);
}
