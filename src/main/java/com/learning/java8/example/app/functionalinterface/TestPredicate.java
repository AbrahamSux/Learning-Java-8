package com.learning.java8.example.app.functionalinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestPredicate {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPredicate.class);



    // MAIN.

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n se pasa como parámetro para probar el método de la interfaz Predicate
        // El método de prueba siempre devolverá verdadero sin importar el valor que tenga n.

        // Pasar n como parámetro.
        evalue(integerList, n -> true);



        // Predicate<Integer> predicate = n -> n % 2 == 0
        // n se pasa como parámetro para probar el método de la interfaz Predicate
        // El método de prueba devolverá verdadero si n% 2 llega a ser cero.

        LOGGER.info("Imprimir números pares:");
        evalue(integerList, n -> n % 2 == 0); // 'n' Resto de la División entre '2' igual a 'CERO'.



        // Predicate<Integer> predicate = n -> n> 3
        // n se pasa como parámetro para probar el método de la interfaz Predicate
        // El método de prueba devolverá verdadero si n es mayor que 3.

        LOGGER.info("Imprimir números mayores que 3:");
        evalue(integerList, n -> n > 3);
    }



    // MÉTODOS.

    public static void evalue(List<Integer> list, Predicate<Integer> predicate) {

        for (Integer value : list) {
            if (predicate.test(value)) {
                LOGGER.info("Value : {}", value);
            }
        }

    }

}
