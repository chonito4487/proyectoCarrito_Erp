package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Cliente;
import com.empresa.erpventas.entities.Venta;
import com.empresa.erpventas.service.ClienteService;
import com.empresa.erpventas.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;
    private final ClienteService cliService;


    public VentaController(VentaService service, ClienteService cliService) {
        this.service = service;
        this.cliService = cliService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        return ResponseEntity.ok(service.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVentaPorId(@PathVariable Long id) {
        return service.buascarProId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        if (venta.getCliente() != null && venta.getCliente().getIdCli() != null) {
            Long idCli = venta.getCliente().getIdCli();
            Optional<Cliente> clienteOpt = cliService.buscarPorId(idCli);

            if (clienteOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            venta.setCliente(clienteOpt.get());
        }
        Venta ventaCreado = service.guardarVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> editarVenta(@RequestBody Venta venta, @PathVariable Long id) {
        // veroificamos si existe la venta
        Optional<Venta> ventaOpt = service.buascarProId(id);
        if (ventaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Venta ventaExistente = ventaOpt.get(); //Otenemos la venta

        // verificamos actualizacion del Fk Cliente
        if (venta.getCliente() != null && venta.getCliente().getIdCli() != null) {
            Long idCliente = venta.getCliente().getIdCli();
            Optional<Cliente> clienteOpt = cliService.buscarPorId(idCliente);

            if (clienteOpt.isPresent()) {
                venta.setCliente(clienteOpt.get()); // si existe cliente asiganamos a la venta
            } else {
                return ResponseEntity.badRequest().build();
            }

        }
        //actualizamos
        ventaExistente.setFechaVen(venta.getFechaVen());
        ventaExistente.setHoraVen(venta.getHoraVen());
        ventaExistente.setFormaPago(venta.getFormaPago());

        //Guardamos
        Venta ventaActualizada = service.guardarVenta(ventaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(ventaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Venta>  eliminarVenta(@PathVariable Long id){
        Optional<Venta> ventaOpt = service.eliminarVenta(id);

        if (ventaOpt.isPresent()){
            Venta ventaEliminar = ventaOpt.orElseThrow();
            ResponseEntity.status(HttpStatus.OK).body(ventaEliminar);
        }
        return ResponseEntity.notFound().build();
    }

}
