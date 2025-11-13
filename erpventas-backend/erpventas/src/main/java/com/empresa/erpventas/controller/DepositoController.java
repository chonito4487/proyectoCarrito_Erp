package com.empresa.erpventas.controller;


import com.empresa.erpventas.entities.Deposito;
import com.empresa.erpventas.service.DepositoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depositos")
public class DepositoController {

    private final DepositoService service;

    public DepositoController(DepositoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Deposito>> listarDepositos() {
        return ResponseEntity.ok(service.listarDeposito());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposito> buscarDepoPorId(@PathVariable Long id) {
        Optional<Deposito> depositoOpt = service.buscarPorId(id);
        if (depositoOpt.isPresent()) {
            return ResponseEntity.ok(depositoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Deposito> crearDepo(@RequestBody Deposito deposito) {
        Deposito depositoGuardado = service.guardarDeposito(deposito);
        return ResponseEntity.status(HttpStatus.CREATED).body(depositoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deposito> editarDepo(@RequestBody Deposito deposito, @PathVariable Long id) {

        Optional<Deposito> depositoOpt = service.buscarPorId(id);

        if (depositoOpt.isPresent()) {
            Deposito depositoDb = depositoOpt.get();

            // Actualizar solo los campos permitidos (en este caso, solo el nombre)
            depositoDb.setNombreDepo(deposito.getNombreDepo());

            // Guardar la actualización y retornar 200 OK
            Deposito depositoActualizado = service.guardarDeposito(depositoDb);
            return ResponseEntity.status(HttpStatus.OK).body(depositoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deposito> eliminarDepo(@PathVariable Long id) {
        // Asumimos que el servicio devuelve el objeto eliminado si existía
        Optional<Deposito> depositoOpt = service.eliminarDeposito(id);

        if (depositoOpt.isPresent()) {
            Deposito depoAEliminar = depositoOpt.get();
            return ResponseEntity.status(HttpStatus.OK).body(depoAEliminar);
        }

        return ResponseEntity.notFound().build();
    }


}
