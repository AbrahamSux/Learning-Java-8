package com.learning.java8.example.app.optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class OptionalApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalApp.class);



    // MAIN

    public static void main(String[] args) {
        OptionalApp tester = new OptionalApp();

        // Optional con try-catch de manera tradicional
        tester.process(null);


        // Usando OrElse
        tester.usingOrElse(null);


        // Usando OrElseThrow
        /*tester.usingOrElseThrow(null);*/
        // or
        tester.usingOrElseThrow("Test...");


        //Usando IfPresent
        tester.usingIfPresent(null);
        // or
        tester.usingIfPresent("Element String");
    }



    // METHODS

    public void process(String value) {

        try {
            Optional optional = Optional.empty();
            optional.get();
        } catch (Exception e) {
            LOGGER.error("Error al obtener el elemento, {}", e.getMessage());
        }
    }

    public void usingOrElse(String value) {
        Optional<String> optional = Optional.ofNullable(value);
        String element = optional.orElse("Predeterminado");

        LOGGER.info("Elmento : {}", element);
    }

    public void usingOrElseThrow(String value) {
        Optional<String> optional = Optional.ofNullable(value);
        String element = optional.orElseThrow(NumberFormatException::new);

        LOGGER.info("Cadena : {}", element);
    }

    public void usingIfPresent(String value) {
        Optional<String> optional = Optional.ofNullable(value);

        LOGGER.info("Existe Elemento ? : {} : {}", optional.isPresent(), optional.orElse("NULL"));
    }

}
