package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.DetalleVenta;
import com.empresa.erpventas.repository.DetalleVentaRepository;
import com.empresa.erpventas.service.DetalleVentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    final private DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleVenta> listarDetallesVenta() {
        return (List<DetalleVenta>) detalleVentaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Transactional
    @Override
    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Transactional
    @Override
    public Optional<DetalleVenta> eliminarDetalleVenta(Long id) {
        Optional<DetalleVenta> detalleOpt = detalleVentaRepository.findById(id);
        if(detalleOpt.isPresent()){
            detalleVentaRepository.deleteById(id);
            return detalleOpt;
        }

        return Optional.empty();
    }
}
