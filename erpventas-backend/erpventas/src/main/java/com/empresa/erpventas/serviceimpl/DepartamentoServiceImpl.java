package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Departamento;
import com.empresa.erpventas.repository.DepartamentoRepository;
import com.empresa.erpventas.service.DepartamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    final private DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Departamento> listarDepartamento() {
        return (List<Departamento>) departamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Departamento> buscarPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    @Transactional
    @Override
    public Departamento guardarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Transactional
    @Override
    public Optional<Departamento> eliminarDepartamento(Long id) {
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);
        if(departamentoOpt.isPresent()){
            departamentoRepository.deleteById(id);
            return departamentoOpt;
        }

        return Optional.empty();
    }
}
