package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Carrito;
import com.empresa.erpventas.repository.CarritoRepository;
import com.empresa.erpventas.service.CarritoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoServiceImpl(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
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

        if(carritoOpt.isPresent()){
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
}



