package com.learning.java8.example.app.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StreamApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamApp.class);



    private List<String> listaNombres;
    private List<String> listaNumeros;

    // CONSTRUCTOR.

    public StreamApp() {
        listaNombres = new ArrayList<>();
        listaNombres.add("Yelena");
        listaNombres.add("Diana");
        listaNombres.add("Abraham");
        listaNombres.add("Dayana");

        listaNumeros = new ArrayList<>();
        listaNumeros.add("5");
        listaNumeros.add("2");
        listaNumeros.add("4");
        listaNumeros.add("3");
    }



    // MAIN.

    public static void main(String[] args) {

        StreamApp tester = new StreamApp();

        LOGGER.info(">>> Filtrando Elementos...");
        tester.filtrar();

        LOGGER.info(">>> Ordenando Elementos...");
        tester.ordenar();

        LOGGER.info(">>> Transformando Elementos...");
        tester.transformar();

        LOGGER.info(">>> Limitando Elementos...");
        tester.limitar();

        LOGGER.info(">>> Contando Elementos...");
        tester.contar();
    }



    // MÉTODOS.

    public void filtrar() {
        //Filtrar nombres que empiezan con 'D'.
        listaNombres.stream().filter(elements -> elements.startsWith("D")).forEach(LOGGER::info);
    }

    public void ordenar() {
        // Ascendente
        /*listaNombres.stream().sorted().forEach(LOGGER::info);*/

        // Descendente
        listaNombres.stream().sorted(Comparator.reverseOrder()).forEach(LOGGER::info);
    }

    public void transformar() {
        // Convirtiendo a mayúsculas.
        listaNombres.stream().map(String::toUpperCase).sorted().forEach(LOGGER::info);

        // Convierte una lista de String a Integer y le suma 1 a cada número de la lista.
        listaNumeros.stream().map(n -> Integer.parseInt(n) + 1).sorted().forEach(element -> {
            LOGGER.info("Lista de Números : {}", element);
        });
    }

    public void limitar() {
        listaNombres.stream().limit(2).forEach(LOGGER::info);
    }

    public void contar() {
        long count = listaNombres.stream().count();
        LOGGER.info("{} elementos.", count);
    }

}
