package com.learning.java8.example.app.lambda.calculos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealizarOperacion {

    public static final Logger LOGGER = LoggerFactory.getLogger(RealizarOperacion.class);

    public static void main(String[] args) {

        // Using Java 7
        /*Operacion operacion = new Operacion() {
            @Override
            public double calcularPromedio(double valueOne, double evalueTwo) {
                return (valueOne + evalueTwo) / 2;
            }
        };*/

        // Using Java 8
        Operacion operacion = (double x, double y) -> (x+y)/2;

        LOGGER.info("Promedio : {}", operacion.calcularPromedio(7, 8));
    }

}
