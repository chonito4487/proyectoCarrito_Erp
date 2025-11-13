package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Ciudad;
import com.empresa.erpventas.repository.CiudadRepository;
import com.empresa.erpventas.service.CiudadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ciudad> listarCiudad() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ciudad> buscarPorId(Long id) {
        return ciudadRepository.findById(id);
    }

    @Transactional
    @Override
    public Ciudad guardarCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    @Transactional
    @Override
    public Optional<Ciudad> eliminarCiudad(Long id) {
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(id);
        if (ciudadOpt.isPresent()) {
            ciudadRepository.deleteById(id);
            return ciudadOpt;
        }
        return Optional.empty();
    }
}
