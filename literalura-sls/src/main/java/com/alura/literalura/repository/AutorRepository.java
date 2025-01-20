package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllAutores();
    @Query("SELECT a from Autor a LEFT JOIN a.libros WHERE (a.anyoDeceso IS NULL) AND a.anyoNacimiento <=anyo")
    List<Autor> encontrarAutorVivo(@Param("anyo")int anyo);

    Optional<Autor> findByNombre(String nombre);
}
