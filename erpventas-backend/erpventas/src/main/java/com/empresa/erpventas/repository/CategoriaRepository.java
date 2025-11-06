package com.empresa.erpventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.erpventas.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
