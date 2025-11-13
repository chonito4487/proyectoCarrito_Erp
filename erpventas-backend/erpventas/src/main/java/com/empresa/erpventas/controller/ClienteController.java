package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Ciudad;
import com.empresa.erpventas.entities.Cliente;
import com.empresa.erpventas.service.CiudadService;
import com.empresa.erpventas.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;
    private final CiudadService ciudadService;

    public ClienteController(ClienteService service, CiudadService ciudadService) {
        this.service = service;
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(service.listarCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        if (cliente.getCiudad() != null && cliente.getCiudad().getIdCiu() != null) {
            Long idCiu = cliente.getCiudad().getIdCiu();
            Optional<Ciudad> ciudadOpt = ciudadService.buscarPorId(idCiu);

            if (ciudadOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            cliente.setCiudad(ciudadOpt.get());
        }
        Cliente clienteCreado = service.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
        Optional<Cliente> clienteOpt = service.buscarPorId(id);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (cliente.getCiudad() != null && cliente.getCiudad().getIdCiu() != null) {
            Optional<Ciudad> ciudadOpt = ciudadService.buscarPorId(cliente.getCiudad().getIdCiu());

            if (ciudadOpt.isPresent()) {
                cliente.setCiudad(ciudadOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Cliente clienteEditado = service.actualizarCliente(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ciudad> eliminarCli(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = service.eliminarCliente(id);

        if (clienteOpt.isPresent()) {
            Cliente clienteEliminar = clienteOpt.orElseThrow();
            ResponseEntity.status(HttpStatus.OK).body(clienteEliminar);
        }
        return ResponseEntity.notFound().build();
    }

}
