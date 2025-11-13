package com.empresa.erpventas.repository;

import com.empresa.erpventas.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository <Carrito, Long> {
    List<Carrito> findByClienteId(Long idCliente); //agregamos manualmente, coinsulta personalizada
}
