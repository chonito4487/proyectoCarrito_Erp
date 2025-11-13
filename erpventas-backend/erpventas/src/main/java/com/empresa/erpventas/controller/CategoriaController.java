package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Categoria;
import com.empresa.erpventas.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catgorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(service.listarCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOpt = service.buscarPorId(id);

        if (categoriaOpt.isPresent()) {
            return ResponseEntity.ok(categoriaOpt.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarCategoria(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        Optional<Categoria> categoriaOpt = service.buscarPorId(id);

        if (categoriaOpt.isPresent()) {
            Categoria categoriaDb = categoriaOpt.orElseThrow();
            categoriaDb.setNombreCat(categoria.getNombreCat());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarCategoria(categoria));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaOpt = service.eliminarCategoria(id);

        if (categoriaOpt.isPresent()) {
            Categoria categoriaEliminar = categoriaOpt.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(categoriaEliminar);
        }

        return ResponseEntity.notFound().build();

    }


}
