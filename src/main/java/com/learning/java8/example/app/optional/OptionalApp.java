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
        LOGGER.info(">>> Usando Optional.empty()... ");
        tester.process(null);


        // Usando OrElse
        LOGGER.info(">>> Usando OrElse... ");
        tester.usingOrElse(null);


        // Usando OrElseThrow
        LOGGER.info(">>> Usando OrElseThrow... ");
        /*tester.usingOrElseThrow(null);*/
        // or
        tester.usingOrElseThrow("Test...");


        // Usando IfPresent
        LOGGER.info(">>> Usando IfPresent... ");
        tester.usingIfPresent(null);
        // or
        tester.usingIfPresent("Element String");


        // Diferencia entre OrElseGet y OrElse
        LOGGER.info(">>> Usando 'OrElseGet' And 'OrElse' Differ... ");
        tester.whenOrElseGetAndOrElseDiffer_thenCorrect();
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

    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        LOGGER.info("> Usando orElseGet :");
        String defaultTextOne = Optional.ofNullable(text).orElseGet(this::obtenerCadena);
        LOGGER.info("Text present : {}", defaultTextOne);


        LOGGER.info("> Usando orElse :");
        String defaultTextTwo = Optional.ofNullable(text).orElse(obtenerCadena());
        LOGGER.info("Text present : {}", defaultTextTwo);

        /**
         * Tenga en cuenta que cuando se usa orElseGet() para recuperar el valor envuelto, el método getMyDefault()
         * ni siquiera se invoca ya que el valor contenido está presente.
         *
         * Sin embargo, cuando se usa orElse(), ya sea que el valor envuelto esté presente o no, se crea el objeto
         * predeterminado. Entonces, en este caso, acabamos de crear un objeto redundante que nunca se usa.
         *
         * En este ejemplo simple, no hay un costo significativo para crear un objeto predeterminado, ya que la JVM
         * sabe cómo manejarlo. Sin embargo, cuando un método como getMyDefault() tiene que realizar una llamada a un
         * servicio web o incluso consultar una base de datos, el costo se vuelve muy obvio.
         */
    }

    public String obtenerCadena() {
        LOGGER.debug("Getting Default Value");
        return "Default Value";
    }

}
