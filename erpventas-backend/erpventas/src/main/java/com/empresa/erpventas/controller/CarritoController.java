package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Carrito;
import com.empresa.erpventas.entities.Cliente;
import com.empresa.erpventas.service.CarritoService;
import com.empresa.erpventas.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    private final CarritoService service;
    private final ClienteService clienteService;

    public CarritoController(CarritoService service, ClienteService clienteService) {
        this.service = service;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> listarCarritos() {
        return ResponseEntity.ok(service.listarCarritos());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Carrito>> listarCarritosPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.listarCarritosPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carrito> guardarCarrito(@RequestBody Carrito carrito) {

        // validacion de clientes (FK)
        if (carrito.getCliente() == null || carrito.getCliente().getIdCli() == null) {
            return ResponseEntity.badRequest().build(); // Cliente es obligatorio
        }

        Long idCli = carrito.getCliente().getIdCli();
        Optional<Cliente> clienteOpt = clienteService.buscarPorId(idCli);

        if (clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Cliente no existe
        }
        carrito.setCliente(clienteOpt.get());

        // Guardar
        Carrito carritoGuardado = service.guardarCarrito(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> editarCarrito(@RequestBody Carrito carrito, @PathVariable Long id) {

        Optional<Carrito> carritoOpt = service.buscarPorId(id);
        if (carritoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Carrito carritoDb = carritoOpt.get();

        // Aactualizacion cliente (Opcional en PUT)
        if (carrito.getCliente() != null && carrito.getCliente().getIdCli() != null) {
            Long idCliente = carrito.getCliente().getIdCli();
            Optional<Cliente> clienteOpt = clienteService.buscarPorId(idCliente);

            if (clienteOpt.isPresent()) {
                carritoDb.setCliente(clienteOpt.get());
            } else {
                return ResponseEntity.badRequest().build(); // Cliente inválido
            }
        }
        // Guardar la actualización
        Carrito carritoActualizado = service.guardarCarrito(carritoDb);
        return ResponseEntity.status(HttpStatus.OK).body(carritoActualizado);
    }

    // Método para vaciar los detalles del carrito
    @PutMapping("/vaciar/{idCarrito}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long idCarrito) {
        Optional<Carrito> carritoOpt = service.buscarPorId(idCarrito);
        if (carritoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.vaciarCarrito(idCarrito);
        return ResponseEntity.noContent().build(); // 204 No Content es común para una acción sin cuerpo de respuesta
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carrito> eliminarCarrito(@PathVariable Long id) {
        Optional<Carrito> carritoOpt = service.eliminarCarrito(id);

        if (carritoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(carritoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
