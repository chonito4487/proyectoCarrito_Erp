package com.empresa.erpventas.serviceimpl;

import com.empresa.erpventas.entities.Categoria;
import com.empresa.erpventas.repository.CategoriaRepository;
import com.empresa.erpventas.service.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Categoria> listarCategoria() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Transactional
    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional
    @Override
    public Optional<Categoria> eliminarCategoria(Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if(categoriaOpt.isPresent()) {
            categoriaRepository.deleteById(id);
            return categoriaOpt;
        }
        return Optional.empty();
    }
}
