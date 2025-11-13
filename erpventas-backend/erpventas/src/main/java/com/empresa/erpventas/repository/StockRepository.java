package com.empresa.erpventas.repository;

import com.empresa.erpventas.entities.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    //Buscar producto por id
    Optional<Stock> findByProductoId(Long idPro);

    // Buscar stock por producto
    List<Stock> findByProducto_IdPro(Long idPro);

    // Buscar stock por producto y depósito
    Optional<Stock> findByProducto_IdProAndDeposito_IdDepo(Long idPro, Long idDepo);
}
