package com.empresa.erpventas.controller;

import com.empresa.erpventas.entities.Categoria;
import com.empresa.erpventas.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:3000")// para react
public class CategoriaController {
    public final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar () {
        return categoriaService.listar();
    }

    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }


}
