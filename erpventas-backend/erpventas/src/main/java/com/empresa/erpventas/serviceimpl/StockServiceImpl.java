package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Stock;
import com.empresa.erpventas.repository.StockRepository;
import com.empresa.erpventas.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    final private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Stock> listarStock() {
        return (List<Stock>) stockRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Stock> buscarPorId(Long id) {
        return stockRepository.findById(id);
    }

    @Override
    public Optional<Stock> buscarPorProductoId(Long idPro) {
        return stockRepository.findByProductoId(idPro);
    }

    @Transactional
    @Override
    public Stock guardarStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    @Override
    public Optional<Stock> eliminarStock(Long id) {
        Optional<Stock> stockOpt = stockRepository.findById(id);
        if(stockOpt.isPresent()){
            stockRepository.deleteById(id);
            return stockOpt;
        }

        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Stock> buscarPorProductoYDeposito(Long idPro, Long idDepo) {
        return stockRepository.findByProducto_IdProAndDeposito_IdDepo(idPro, idDepo);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hayStockDisponible(Long idPro, Long idDepo, int cantidadSolicitada) {
        Optional<Stock> stockOpt = stockRepository.findByProducto_IdProAndDeposito_IdDepo(idPro, idDepo);
        return stockOpt.isPresent() && stockOpt.get().getCantidad() >= cantidadSolicitada;
    }
}
