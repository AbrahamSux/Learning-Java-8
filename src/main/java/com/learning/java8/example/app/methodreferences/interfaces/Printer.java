package com.learning.java8.example.app.methodreferences.interfaces;

@FunctionalInterface
public interface Printer {

    void print();

    default String start() {
        return ">>> Inicia el proceso de impresión...";
    }

    default String end() {
        return "<<< Finaliza el proceso de impresión.";
    }

}
