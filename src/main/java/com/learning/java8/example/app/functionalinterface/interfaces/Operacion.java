package com.learning.java8.example.app.functionalinterface.interfaces;

/**
 * Las interfaces funcionales tienen "una sola funcionalidad" para exhibir.
 */

@FunctionalInterface
public interface Operacion {

    double calcular(double valueOne, double valueTwo);

}
