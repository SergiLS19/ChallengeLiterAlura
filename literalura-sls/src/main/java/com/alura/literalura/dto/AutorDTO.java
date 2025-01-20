package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {
    @JsonAlias("name")
    private String nombre;
    @JsonAlias("birth_year")
    private int anyoNacimiento;
    @JsonAlias("death_year")
    private int anyoDeceso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
