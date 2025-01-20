package com.alura.literalura.services;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.dto.ResultadosLibroDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsumoAPI {
    Scanner sc = new Scanner(System.in);
    ConvierteDatos cD =new ConvierteDatos();
    LibroService ls = new LibroService();
    AutorService as = new AutorService();
    private static String URL_BASE ="https://gutendex.com/books/";


    public String obtenerDatosAPI(String url){
    HttpClient cliente = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
    HttpResponse<String> respuesta = null;

    try {
        respuesta=cliente.send(request,HttpResponse.BodyHandlers.ofString());
    }catch(IOException e){
        throw new RuntimeException("No se pudieron obtener los datos",e);
    }catch(InterruptedException e){
        throw new RuntimeException("La solicitud ha sido interrumpida",e);
    }
    String json=respuesta.body();
    return json;
    }
  public void buscarPorTitulo(String titulo){
      try {
          String apiTitulo= URLEncoder.encode(titulo, StandardCharsets.UTF_8);
          String json=obtenerDatosAPI(URL_BASE+"?search="+apiTitulo);
          ResultadosLibroDTO rlDTO=cD.obtenerDatos(json,ResultadosLibroDTO.class);
          List<LibroDTO> libroDTOS=rlDTO.getLibros();
          if (libroDTOS.isEmpty()){
              System.out.println("El libro no ha sido encontrado");
          }else {
              boolean libroRegistrado=false;
              for (LibroDTO libroDTO:libroDTOS){
                  if (libroDTO.getTitulo().equalsIgnoreCase(titulo)){
                      Optional<Libro> libroHallado = ls.encontrarLibroPorTitulo(titulo);
                      if (libroHallado.isPresent()){
                          System.out.println("El libro "+titulo+" ya ha sido registrado");
                          System.out.println("Puede encontrarlo en su historial");
                          libroRegistrado=true;
                          break;
                      } else {
                          Libro libro = new Libro();
                          libro.setTitulo(libroDTO.getTitulo());
                          libro.setIdioma(libroDTO.getIdioma().get(0));
                          libro.setCantDescargas(libroDTO.getNumDescargas());

                          AutorDTO primerAutorDTO = libroDTO.getAutores().get(0);
                          Optional<Autor> autor = as.encontrarAutorPorNombre(primerAutorDTO.getNombre());
                          if (autor.isEmpty()){
                              System.out.println("El autor no existe o no ha sido encontrado");
                          }
                        ls.registrarLibro(libro);
                          System.out.println("Libro registrado: "+libro.getTitulo());
                          libroRegistrado= true;
                      }
                  }
              }
              if (!libroRegistrado){
                  System.out.println("No se encontro un libro con el titulo "+titulo);
              }
          }
      }catch (Exception e){
          System.out.println("Error al obtener los datos: "+e.getMessage());
      }
  }
}
