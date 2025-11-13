package com.empresa.erpventas.service;

import com.empresa.erpventas.entities.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

List<Departamento> listarDepartamento();
Optional<Departamento> buscarPorId(Long id);
Departamento guardarDepartamento(Departamento departamento);
Optional<Departamento> eliminarDepartamento(Long id);

}
