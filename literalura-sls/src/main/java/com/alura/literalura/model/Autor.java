package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int anyoNacimiento;
    private int anyoDeceso;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public int getAnyoDeceso() {
        return anyoDeceso;
    }

    public void setAnyoDeceso(int anyoDeceso) {
        this.anyoDeceso = anyoDeceso;
    }

    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(int anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Año de nacimiento=" + anyoNacimiento +
                ", Año de deceso=" + anyoDeceso +
                ", Libros=" + libros +
                '}';
    }
}
