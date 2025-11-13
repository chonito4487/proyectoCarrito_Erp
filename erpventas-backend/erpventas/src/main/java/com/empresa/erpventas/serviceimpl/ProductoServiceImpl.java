package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Producto;
import com.empresa.erpventas.repository.ProductoRepository;
import com.empresa.erpventas.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> listarProducto() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional
    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    @Override
    public Optional<Producto> eliminarProducto(Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if(productoOpt.isPresent()){
            productoRepository.deleteById(id);
            return productoOpt;
        }
        return Optional.empty();
    }
}
