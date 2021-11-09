package com.learning.java8.example.app.defaultmethods;

import com.learning.java8.example.app.defaultmethods.interfaces.DefaultMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDefaultMethod implements DefaultMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDefaultMethod.class);



    // MAIN.

    public static void main(String[] args) {

        TestDefaultMethod testDefaultMethod = new TestDefaultMethod();

        // Implementación de la interface.
        testDefaultMethod.caminar();

        // Impresión del Método Predeterminado (Default Method).
        LOGGER.info(testDefaultMethod.trotar());

    }



    // MÉTODOS.

    @Override
    public void caminar() {
        LOGGER.info("Caminando...");
    }

}
