package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarCliente();
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    Optional<Cliente> eliminarCliente(Long id);
}

