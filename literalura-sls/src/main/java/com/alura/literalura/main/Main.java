package com.alura.literalura.main;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.services.AutorService;
import com.alura.literalura.services.ConsumoAPI;
import com.alura.literalura.services.ConvierteDatos;
import com.alura.literalura.services.LibroService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private LibroService ls;
    private AutorService as;
    private ConsumoAPI consAPI;
    private ConvierteDatos convDatos;

    public void Menu(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("************ LiterAlura ************");
            System.out.println("Un sitio donde puedes encontrar informacion sobre ese libro que tanto buscas");
            System.out.println("************************************");
            System.out.println("1. Buscar libro por su titulo");
            System.out.println("2. Listar libros buscados");
            System.out.println("3. Listar libros por su idioma");
            System.out.println("4. Listar autores por libros buscados");
            System.out.println("5. Listar autores vivos en un año determinado");
            System.out.println("6. Salir");
            System.out.println("Por favor, elija el numero segun la opcion que desee");
            opcion=sc.nextInt();
            switch (opcion){
                case 1:{
                    System.out.println("Ingrese el titulo del libro");
                    String titulo = sc.next();
                    consAPI.buscarPorTitulo(titulo);
                    break;
                }
                case 2:{
                    ls.listarLibros().forEach(libro -> {
                        System.out.println("******** Libro ********");
                        System.out.println("Titulo: "+libro.getTitulo());
                        System.out.println("Genero: "+libro.getGenero());
                        System.out.println("Autor: "+libro.getAutor());
                        System.out.println("Idioma: "+libro.getIdioma());
                        System.out.println("Numero de descargas: "+libro.getCantDescargas());
                    });
                    break;
                }
                case 3:{
                    System.out.println("""
                            Ingrese el idioma:
                            - Español (es)
                            - Ingles (en)
                            - Frances (fr)
                            - Aleman (al)
                            """);
                    String idioma = sc.next();
                    if ("es".equalsIgnoreCase(idioma)|| "en".equalsIgnoreCase(idioma)||"fr".equalsIgnoreCase(idioma)||"al".equalsIgnoreCase(idioma)){
                        ls.listarLibrosPorIdioma(idioma).forEach(libro -> {
                            System.out.println("******** Libro ********");
                            System.out.println("Titulo: "+libro.getTitulo());
                            System.out.println("Genero: "+libro.getGenero());
                            System.out.println("Autor: "+libro.getAutor());
                            System.out.println("Idioma: "+libro.getIdioma());
                            System.out.println("Numero de descargas: "+libro.getCantDescargas());
                        });
                    }else {
                        System.out.println("Idioma no valido");
                    }
                    break;
                }
                case 4:{
                    as.listarAutores().forEach(autor -> {
                        System.out.println("******** Autor ********");
                        System.out.println("Nombre: "+autor.getNombre());
                        System.out.println("Fecha de nacimiento: "+autor.getAnyoNacimiento());
                        System.out.println("Fecha de deceso: "+autor.getAnyoDeceso());
                        String librosDelAutor=autor.getLibros().stream()
                                .map(Libro::getTitulo)
                                .collect(Collectors.joining(", "));
                        System.out.println("Libros del autor: "+librosDelAutor);

                    });
                    break;
                }
                case 5: {
                    System.out.println("Ingrese el año en el que quiere buscar autores");
                    int anyo =sc.nextInt();
                    List<Autor> autoresVivos=as.listarAutoresVivosEnAnyo(anyo);
                    if (autoresVivos.isEmpty()){
                        System.out.println("Creo que hasta ese año varios autores pasaron a mejor vida :(");
                    }else {
                        autoresVivos.forEach(autor ->{
                            System.out.println("******** Autor ********");
                            System.out.println("Nombre: "+autor.getNombre());
                            System.out.println("Fecha de nacimiento: "+autor.getAnyoNacimiento());
                            System.out.println("Fecha de deceso: "+autor.getAnyoDeceso());
                            System.out.println("Libros del autor: "+autor.getLibros().size());
                        });
                    }
                    break;
                }
                case 6:
                    System.out.println("Saliendo...");
                default:
                    System.out.println("Opcion no valida.");
            }
        }while (opcion!=6);
    }
}
