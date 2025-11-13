package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Deposito;
import java.util.List;
import java.util.Optional;

public interface DepositoService {
    List<Deposito> listarDeposito();
    Optional<Deposito> buscarPorId(Long id);
    Deposito guardarDeposito(Deposito deposito);
    Optional<Deposito> eliminarDeposito(Long id);
}
