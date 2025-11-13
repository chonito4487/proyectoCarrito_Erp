package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.*;
import com.empresa.erpventas.repository.*;
import com.empresa.erpventas.service.CarritoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final DetalleCarritoRepository detalleCarritoRepository;
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final StockRepository stockRepository;


    public CarritoServiceImpl(CarritoRepository carritoRepository, DetalleCarritoRepository detalleCarritoRepository,
                              VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository, StockRepository stockRepository) {
        this.carritoRepository = carritoRepository;
        this.detalleCarritoRepository = detalleCarritoRepository;
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Carrito> listarCarritos() {
        return (List<Carrito>) carritoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Carrito> buscarPorId(Long id) {
        return carritoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Carrito> listarCarritosPorCliente(Long idCliente) {
        return carritoRepository.findByClienteId(idCliente);
    }

    @Transactional
    @Override
    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Transactional
    @Override
    public Optional<Carrito> eliminarCarrito(Long id) {
        Optional<Carrito> carritoOpt = carritoRepository.findById(id);

        if (carritoOpt.isPresent()) {
            carritoRepository.deleteById(id);
            return carritoOpt;
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public void vaciarCarrito(Long idCarrito) {
        Optional<Carrito> carritoOpt = carritoRepository.findById(idCarrito);

        /*Programacion funcional
        carritoOpt.ifPresent(carrito -> {
            carrito.getDetalles().clear();
            carritoRepository.save(carrito);
        });
        */

        if (carritoOpt.isPresent()) {
            Carrito carrito = carritoOpt.get(); // Obtenemos la instancia de Carrito
            carrito.getDetalles().clear();  // Vacía la colección de detalles
            carritoRepository.save(carrito); // Guardamos el carrito
        }
    }

    @Override
    public Venta procesarCompra(Long idCliente) {
        // Buscamos el carrito activo del cliente
        List<Carrito> carritos = carritoRepository.findByClienteId(idCliente);
        if (carritos.isEmpty()) {
            throw new RuntimeException("No se encontró un carrito para el cliente.");
        }

        Carrito carrito = carritos.get(0);

        // Obtenemos los detalles del carrito
        List<DetalleCarrito> detallesCarrito = detalleCarritoRepository.findByCarrito_IdCarrito(carrito.getIdCarrito());
        if (detallesCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío.");
        }

        // Creamos la venta
        Venta venta = new Venta();
        venta.setCliente(carrito.getCliente());
        venta.setFechaVen(LocalDate.now());
        venta.setHoraVen(LocalTime.now());
        venta.setFormaPago(carrito.getFormaPagoCarri());
        ventaRepository.save(venta);

        // Convertimos cada DetalleCarrito → DetalleVenta
        for (DetalleCarrito detCar : detallesCarrito) {
            DetalleVenta detVenta = new DetalleVenta();
            detVenta.setVenta(venta);
            detVenta.setProducto(detCar.getProducto());
            detVenta.setDeposito(detCar.getDeposito());
            detVenta.setCantidad(detCar.getCantidad());
            detVenta.setPrecioUnitario(detCar.getPrecioUnitario());
            detVenta.setSubtotal(detCar.getSubtotal());
            detalleVentaRepository.save(detVenta);

            // Buscamos el stock del producto en el depósito correspondiente
            Optional<Stock> stockOpt = stockRepository.findByProducto_IdProAndDeposito_IdDepo(
                    detCar.getProducto().getIdPro(),
                    detCar.getDeposito().getIdDepo()
            );

            if (stockOpt.isEmpty()) {
                throw new RuntimeException("No se encontró stock para el producto: "
                        + detCar.getProducto().getNombrePro());
            }

            Stock stock = stockOpt.get();

            // Validamos si hay suficiente stock
            if (stock.getCantidad() < detCar.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: "
                        + detCar.getProducto().getNombrePro());
            }

            // Descontamos cantidad
            stock.setCantidad(stock.getCantidad() - detCar.getCantidad());
            stockRepository.save(stock);
        }

        return venta;

    }
}



