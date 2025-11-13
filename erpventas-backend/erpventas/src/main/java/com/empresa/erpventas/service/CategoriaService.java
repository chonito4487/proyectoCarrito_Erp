package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listarCategoria();
    Optional<Categoria> buscarPorId(Long id);
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> eliminarCategoria(Long id);
}
