package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Ciudad;
import com.empresa.erpventas.entities.Departamento;
import com.empresa.erpventas.service.CiudadService;
import com.empresa.erpventas.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService service;
    private final DepartamentoService dptoService;

    public CiudadController(CiudadService service, DepartamentoService dptoService) {
        this.service = service;
        this.dptoService = dptoService;
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarCiudades() {
        return ResponseEntity.ok(service.listarCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarPorId(@PathVariable Long id) {
        Optional<Ciudad> ciudadOpt = service.buscarPorId(id);
        if (ciudadOpt.isPresent()) {
            return ResponseEntity.ok(ciudadOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Ciudad> guardarCiudad(@RequestBody Ciudad ciudad) {
        if(ciudad.getDepartamento() != null && ciudad.getDepartamento().getIdDepto() != null){
            Long idDepa = ciudad.getDepartamento().getIdDepto(); //Guradamos id del departamento
            Optional<Departamento> depOtp = dptoService.buscarPorId(idDepa); //Buscamos departamento por id

            if(depOtp.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            //Asignamos departamento a ciudad para guardar
            ciudad.setDepartamento(depOtp.get());
        }

        // Guardamos ciudad
        Ciudad ciudadGuardada = service.guardarCiudad(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadGuardada);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> editarCiu(@RequestBody Ciudad ciudad, @PathVariable Long id) {

        Optional<Ciudad> ciudadOpt = service.buscarPorId(id);
        if (ciudadOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ciudad ciudadDb = ciudadOpt.get();
        ciudadDb.setNombreCiu(ciudad.getNombreCiu());

        if (ciudad.getDepartamento() != null && ciudad.getDepartamento().getIdDepto() != null) {
            Long idDepartamento = ciudad.getDepartamento().getIdDepto();
            Optional<Departamento> depOpt = dptoService.buscarPorId(idDepartamento);
            if (depOpt.isPresent()) {
                ciudadDb.setDepartamento(depOpt.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Ciudad ciudadActualizada = service.guardarCiudad(ciudadDb);
        return ResponseEntity.status(HttpStatus.OK).body(ciudadActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ciudad> eliminarCiu(@PathVariable Long id) {
        Optional<Ciudad> ciudadOpt = service.eliminarCiudad(id);

        if (ciudadOpt.isPresent()) {
            Ciudad ciudadEliminar = ciudadOpt.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(ciudadEliminar);
        }
        return ResponseEntity.notFound().build();
    }


}
