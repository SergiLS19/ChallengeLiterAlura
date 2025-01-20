package com.alura.literalura.services;

import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private AutorRepository ar;
    public List<Autor> listarAutores(){
        return ar.findAllAutores();
    }
    public List<Autor> listarAutoresVivosEnAnyo(int anyo){
        return ar.encontrarAutorVivo(anyo);
    }
    public Optional<Autor> encontrarAutorPorId(Long id){
        return ar.findById(id);
    }
    public Optional<Autor> encontrarAutorPorNombre(String nombre){
        return ar.findByNombre(nombre);
    }

}
