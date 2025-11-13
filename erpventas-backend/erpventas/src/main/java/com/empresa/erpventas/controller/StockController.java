package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Deposito;
import com.empresa.erpventas.entities.Producto;
import com.empresa.erpventas.entities.Stock;
import com.empresa.erpventas.service.DepositoService;
import com.empresa.erpventas.service.ProductoService;
import com.empresa.erpventas.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;
    private final ProductoService productoService;
    private final DepositoService depositoService;

    public StockController(StockService stockService, ProductoService productoService, DepositoService depositoService) {
        this.stockService = stockService;
        this.productoService = productoService;
        this.depositoService = depositoService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> listarStock() {
        return ResponseEntity.ok(stockService.listarStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> buscarPorId(@PathVariable Long id) {
        Optional<Stock> stockOpt = stockService.buscarPorId(id);
        if (stockOpt.isPresent()) {
            return ResponseEntity.ok(stockOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/producto/{idPro}")
    public ResponseEntity<Stock> buscarPorProductoId(@PathVariable Long idPro) {
        return stockService.buscarPorProductoId(idPro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> guardarStock(@RequestBody Stock stock) {

        if (stock.getProducto() == null || stock.getProducto().getIdPro() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long idPro = stock.getProducto().getIdPro();
        Optional<Producto> productoOpt = productoService.buscarPorId(idPro);

        if (productoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Producto no existe
        }
        stock.setProducto(productoOpt.get());

        if (stock.getDeposito() == null || stock.getDeposito().getIdDepo() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long idDepo = stock.getDeposito().getIdDepo();
        Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);

        if (depositoOpt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Depósito no existe
        }
        stock.setDeposito(depositoOpt.get());

        Stock stockGuardado = stockService.guardarStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> editarStock(@RequestBody Stock stock, @PathVariable Long id) {

        Optional<Stock> stockOpt = stockService.buscarPorId(id);
        if (stockOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Stock stockDb = stockOpt.get();

        if (stock.getProducto() != null && stock.getProducto().getIdPro() != null) {
            Long idPro = stock.getProducto().getIdPro();
            Optional<Producto> productoOpt = productoService.buscarPorId(idPro);

            if (productoOpt.isPresent()) {
                stockDb.setProducto(productoOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        if (stock.getDeposito() != null && stock.getDeposito().getIdDepo() != null) {
            Long idDepo = stock.getDeposito().getIdDepo();
            Optional<Deposito> depositoOpt = depositoService.buscarPorId(idDepo);

            if (depositoOpt.isPresent()) {
                stockDb.setDeposito(depositoOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        if (stock.getCantidad() != null) {
            stockDb.setCantidad(stock.getCantidad());
        }

        Stock stockActualizado = stockService.guardarStock(stockDb);
        return ResponseEntity.status(HttpStatus.OK).body(stockActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stock> eliminarStock(@PathVariable Long id) {
        Optional<Stock> stockOpt = stockService.eliminarStock(id);

        if (stockOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(stockOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
