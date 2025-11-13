package com.empresa.erpventas.repository;

import com.empresa.erpventas.entities.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCarritoRepository extends JpaRepository <DetalleCarrito, Long> {
    List<DetalleCarrito> findByCarrito_IdCarrito(Long idCarrito);
    void deleteByCarrito_IdCarrito(Long idCarrito);
}
