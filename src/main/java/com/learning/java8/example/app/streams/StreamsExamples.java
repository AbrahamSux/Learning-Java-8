package com.learning.java8.example.app.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class StreamsExamples {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExamples.class);



    // MAIN

    public static void main(String[] args) {
        StreamsExamples tester = new StreamsExamples();

        LOGGER.info(">>> Usando Random (ForEach)...");
        tester.usingForEach();

        LOGGER.info(">>> Usando Map...");
        tester.usingMap();

        LOGGER.info(">>> Usando Filter...");
        tester.usingFilter();

        LOGGER.info(">>> Usando Limit...");
        tester.usingLimit();

        LOGGER.info(">>> Usando Sorted...");
        tester.usingSorted();

        LOGGER.info(">>> Usando Parallel Processing");
        tester.usingParallelProcessing();

        LOGGER.info(">>> Usando Collectors");
        tester.usingCollectors();

        LOGGER.info(">>> Usando Statistics");
        tester.usingStatistics();
    }



    // MÉTODOS

    public void usingForEach() {

        // Random int aleatorio sin rango especificado
        Random random = new Random();
        int [] list = random.ints().limit(5).toArray();
        Arrays.stream(list).forEach(n -> LOGGER.info("{}", n));


        // Números que debe estar dentro del rango de 0 a 10. Límite de 5 números.
        int[] listaRango = new Random().ints(0, 10).limit(5).toArray();
        Arrays.sort(listaRango);
        LOGGER.info("Rango de Números : {}", listaRango);


        // Secuencia de elementos primitivos con valor int, dentro de un rango de 1 a 50.
        IntStream intStream = new Random().ints(1, 50).limit(5);
        List<Integer> integerList = new ArrayList<>();
        intStream.sorted().forEach(n -> {
            LOGGER.info("IntStream : {}", n);

            integerList.add(n);
        });
        LOGGER.info("Lista de Integers : {}", integerList);


        // Combinando tamaño (5) y rango (de 1 a 50).
        DoubleStream doubleStream = new Random().doubles(5, 1, 50);
        doubleStream.sorted().forEach(n -> LOGGER.info("DoubleStream : {}", n));


        // Realizar la suma de una lista de números enteros.
        List<Integer> listaNumeros = Arrays.asList(5, 5, 5, 10);
        int suma = listaNumeros.stream().mapToInt(n -> n).sum();
        LOGGER.info("Suma de Números : {}", suma);
    }

    /**
     * Uso de Transformación.
     */
    public void usingMap() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // Obtener una lista de cuadrados únicos
        List<Integer> squaresList = numbers.stream().map(i -> i * i)//.distinct()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        LOGGER.info("Squares List : {}", squaresList);
    }

    /**
     * Uso de Filtro.
     */
    public void usingFilter() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        // Obtener recuento de las cadenas vacías
        /*long count = strings.stream().filter(string -> string.isEmpty()).count();*/
        long count = strings.stream().filter(String::isEmpty).count();
        LOGGER.info("Número de cadenas vacías : {}", count);
    }

    /**
     * Uso de Límite.
     */
    public void usingLimit() {
        List<Integer> numbers = Arrays.asList(3, 2, 8, 3, 7, 3, 5);

        //numbers.stream().limit(3).forEach(n -> LOGGER.info("Elemento : {}", n));


        List<Integer> integers = new ArrayList<>();
        long maxSize = 3;
        LOGGER.info("Lista sin filtrar : {}", numbers);

        numbers.stream().limit(maxSize).filter( n -> n < 5).forEach(integers::add);

        // Si el límite es 3, entonces solo tomará los 3 primero elementos de la lista: {3, 2, 8},
        // pero como se hace el filtro de que deben ser menores a 5, entonces solo agrega: {3, 2} a la nueva lista.
        LOGGER.info("Lista limitada a '{}' y filtrada a números menores a '5' : {}", maxSize, integers);
    }

    /**
     * Uso de Ordenamiento.
     */
    public void usingSorted() {

        IntStream integers = new Random().ints(1, 100).limit(10).sorted();
        LOGGER.info("Lista de Interos Ordenados : {}", integers.toArray());


        DoubleStream doubles = new Random().doubles(1, 100).limit(5).sorted()
                .map( e -> {
                    BigDecimal value = BigDecimal.valueOf(e);
                    return value.setScale(2, RoundingMode.HALF_UP).doubleValue(); // setScale(Escala, Modo de Redondeo)
                });
        LOGGER.info("Lista de Decimales Ordenados : {}", doubles.toArray());
    }

    /**
     * Uso de Procesamiento en Paralelo.
     */
    public void usingParallelProcessing() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        // Obtener recuento de cadenas vacías
        long count = strings.parallelStream().filter(String::isEmpty).count();
        LOGGER.info("Conteo Paralelo de cadenas vacías : {}", count);
    }

    /**
     * Uso de Collectors.
     */
    public void usingCollectors() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        List<String> filteredList = strings.stream().filter(value -> !value.isEmpty()).collect(Collectors.toList());
        LOGGER.info("Lista Filtrada Obtenida : {}", filteredList);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        LOGGER.info("Lista Combinada en una Cadena : {}", mergedString);


        List<Integer> numbers = Arrays.asList(3, 2, 8, 2, 7, 3, 5);
        int sumNumbers = numbers.stream().filter(n -> n > 0).collect(Collectors.summingInt(n -> n));
        LOGGER.info("Suma de una Lista de Enteros : {}", sumNumbers);
    }

    /**
     * Uso de Estadísticas.
     */
    public void usingStatistics() {
        List<Integer> numbers = Arrays.asList(3, 2, 8, 2, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        LOGGER.info("Número más alto en la lista : {}", stats.getMax());
        LOGGER.info("Número más bajo en la lista : {}", stats.getMin());
        LOGGER.info("Suma de todos los números : {}", stats.getSum());
        LOGGER.info("Promedio de todos los números : {}", stats.getAverage());
    }

}
