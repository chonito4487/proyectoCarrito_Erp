package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> listarStock();
    Optional<Stock> buscarPorId(Long id);
    Optional<Stock> buscarPorProductoId(Long idPro);
    Stock guardarStock(Stock stock);
    Optional<Stock> eliminarStock(Long id);
    Optional<Stock> buscarPorProductoYDeposito(Long idPro, Long idDepo);
    boolean hayStockDisponible(Long idPro, Long idDepo, int cantidadSolicitada);

}
