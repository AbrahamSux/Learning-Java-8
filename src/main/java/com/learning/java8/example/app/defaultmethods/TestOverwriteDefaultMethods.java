package com.learning.java8.example.app.defaultmethods;

import com.learning.java8.example.app.defaultmethods.interfaces.DefaultMethod;
import com.learning.java8.example.app.defaultmethods.interfaces.DefaultMethodTwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestOverwriteDefaultMethods implements DefaultMethod, DefaultMethodTwo {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestOverwriteDefaultMethods.class);



    // MAIN.
    public static void main(String[] args) {
        TestOverwriteDefaultMethods overwriteDefaultMethods = new TestOverwriteDefaultMethods();

        overwriteDefaultMethods.caminar();

        // Impresión del Método Predeterminado Sobrescrito (Default Method).
        LOGGER.info(overwriteDefaultMethods.trotar());
    }



    // MÉTODOS.

    @Override
    public void caminar() {
        LOGGER.info("Caminando...");
    }

    @Override
    public String trotar() {
        //return DefaultMethodTwo.super.trotar();

        // Sobrescribir métodos predeterminados.
        return "Dejando de caminar.";
    }

}
