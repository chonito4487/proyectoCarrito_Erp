package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Departamento;
import com.empresa.erpventas.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departametos")
public class DepartamentoController {
    private final DepartamentoService service;


    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        return ResponseEntity.ok(service.listarDepartamento());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> BuscarDepPorId(@PathVariable Long id) {
        Optional<Departamento> departamentoOpt = service.buscarPorId(id);
        if (departamentoOpt.isPresent()) {
            return ResponseEntity.ok(departamentoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Departamento> CrearDep(@RequestBody Departamento departamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarDepartamento(departamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> editarDep(@RequestBody Departamento departamento, @PathVariable Long id) {

        Optional<Departamento> departamentoOpt = service.buscarPorId(id);

        if (departamentoOpt.isPresent()) {
            Departamento departamentoDb = departamentoOpt.orElseThrow();
            departamentoDb.setNombreDepto(departamento.getNombreDepto());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarDepartamento(departamentoDb));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> eliminarDep(@PathVariable Long id) {
        Optional<Departamento> departamentoOpt = service.eliminarDepartamento(id);
        if (departamentoOpt.isPresent()) {
            Departamento deptoAEliminar = departamentoOpt.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(deptoAEliminar);
        }

        return ResponseEntity.notFound().build();
    }

}
