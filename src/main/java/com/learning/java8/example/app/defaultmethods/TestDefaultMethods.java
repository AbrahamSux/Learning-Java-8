package com.learning.java8.example.app.defaultmethods;

import com.learning.java8.example.app.defaultmethods.interfaces.DefaultMethod;
import com.learning.java8.example.app.defaultmethods.interfaces.DefaultMethodTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDefaultMethods implements DefaultMethod, DefaultMethodTwo {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDefaultMethods.class);



    // MAIN.

    public static void main(String[] args) {
        TestDefaultMethods testDefaultMethods = new TestDefaultMethods();

        testDefaultMethods.caminar();

        // Impresión del Método Predeterminado (Default Method).
        LOGGER.info(testDefaultMethods.trotar());
    }



    // MÉTODOS.

    @Override
    public void caminar() {
        LOGGER.info("Caminando...");
    }

    @Override
    public String trotar() {

        // Apuntando al método de la segunda interface.
        return DefaultMethodTwo.super.trotar();
    }

}
