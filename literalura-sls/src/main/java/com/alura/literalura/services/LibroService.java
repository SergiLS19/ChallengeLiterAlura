package com.alura.literalura.services;

import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private LibroRepository lr;

    public List<Libro> listarLibros(){
        return lr.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma){
        return lr.encontrarPorIdioma(idioma);
    }
    public Libro registrarLibro (Libro libro){
        return lr.save(libro);
    }
    public Optional<Libro> encontrarLibroPorId(Long id){
        return lr.findById(id);
    }

    public Optional<Libro> encontrarLibroPorTitulo(String titulo){
        return lr.findByTitulo(titulo);
    }


}
