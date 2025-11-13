package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> listarStock();
    Optional<Stock> buscarPorId(Long id);
    Stock guardarStock(Stock stock);
    Optional<Stock> eliminarStock(Long id);
}
