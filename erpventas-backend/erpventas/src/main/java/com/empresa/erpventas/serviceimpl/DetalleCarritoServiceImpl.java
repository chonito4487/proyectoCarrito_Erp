package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.DetalleCarrito;
import com.empresa.erpventas.repository.DetalleCarritoRepository;
import com.empresa.erpventas.service.DetalleCarritoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class DetalleCarritoServiceImpl implements DetalleCarritoService {

    private final DetalleCarritoRepository detalleCarritoRepository;

    public DetalleCarritoServiceImpl(DetalleCarritoRepository detalleCarritoRepository) {
        this.detalleCarritoRepository = detalleCarritoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleCarrito> listarDetallesCarrito() {
        return (List<DetalleCarrito>) detalleCarritoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DetalleCarrito> buscarPorId(Long id) {
        return detalleCarritoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleCarrito> listarPorIdCarrito(Long idCarrito) {
        return detalleCarritoRepository.findByCarrito_IdCarrito(idCarrito);
    }

    @Transactional
    @Override
    public DetalleCarrito guardarDetalleCarrito(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    @Transactional
    @Override
    public Optional<DetalleCarrito> eliminarDetalleCarrito(Long id) {
        Optional<DetalleCarrito> detalleOpt = detalleCarritoRepository.findById(id);

        if(detalleOpt.isPresent()){
            detalleCarritoRepository.deleteById(id);
            return detalleOpt;
        }
        return Optional.empty();
    }

}
