package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Venta;
import com.empresa.erpventas.repository.VentaRepository;
import com.empresa.erpventas.service.VentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Venta> listarVentas() {
        return (List<Venta>) ventaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Venta> buascarProId(Long id) {
        return ventaRepository.findById(id);
    }

    @Transactional
    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Transactional
    @Override
    public Optional<Venta> eliminarVenta(Long id) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if(ventaOpt.isPresent()){
            ventaRepository.deleteById(id);
            return ventaOpt;
        }
        return Optional.empty();
    }
}
