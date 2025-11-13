package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.repository.DepositoRepository;
import com.empresa.erpventas.entities.Deposito;
import com.empresa.erpventas.service.DepositoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class DepositoServiceImpl implements DepositoService {
    final private DepositoRepository depositoRepository;

    public DepositoServiceImpl(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Deposito> listarDeposito() {
        return (List<Deposito>) depositoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Deposito> buscarPorId(Long id) {
        return depositoRepository.findById(id);
    }

    @Transactional
    @Override
    public Deposito guardarDeposito(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    @Transactional
    @Override
    public Optional<Deposito> eliminarDeposito(Long id) {
        Optional<Deposito> depositoOpt = depositoRepository.findById(id);
        if(depositoOpt.isPresent()){
            depositoRepository.deleteById(id);
            return depositoOpt;
        }

        return Optional.empty();
    }
}
