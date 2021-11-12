package com.learning.java8.example.app.optional;

import com.learning.java8.example.app.models.dto.OperacionDTO;
import com.learning.java8.example.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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


        // Usando List<Optional<Object>>
        LOGGER.info(">>> Usando List<Optional<Object>>... ");
        List<Optional<OperacionDTO>> listOptional = tester.usingListOptional();
        if (listOptional != null && !listOptional.isEmpty()) {
            LOGGER.info("Lista de Elementos Opcionales : {}", listOptional);
        }


        // Usando Optional<List<OperacionDTO>>
        LOGGER.info(">>> Usando Optional<List<OperacionDTO>>... ");
        Optional<List<OperacionDTO>> optionalList = tester.usingOptionalList();
        if (optionalList.isPresent()) {
            LOGGER.info("Optional List : {}", optionalList);
        }
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

    /**
     * Ejemplo del uso de una Lista de Optional.
     *
     * @return List<Optional<OperacionDTO>>
     */
    public List<Optional<OperacionDTO>> usingListOptional() {

        List<OperacionDTO> listaOperaciones = new ArrayList<>(Constants.listOfOperations);
        List<Optional<OperacionDTO>> optional = new ArrayList<>();

        // Lista Normal
        listaOperaciones.stream().sorted(Comparator.comparing(OperacionDTO::getId).reversed()).forEach( e -> {
            // LOGGER.info("Elemento Normal : {}", e);

            optional.add(Optional.of(e));
        });

        // Lista de Optional
        if (!optional.isEmpty()) {
            optional.forEach( e -> LOGGER.info("Element Optional : {}", e));
        }

        return optional;
    }

    /**
     * Ejemplo del uso de un Optional con una Lista.
     *
     * @return Optional<List<OperacionDTO>>
     */
    public Optional<List<OperacionDTO>> usingOptionalList() {

        List<OperacionDTO> listaOperaciones = new ArrayList<>(Constants.listOfOperations);
        Optional<List<OperacionDTO>> optionalList = Optional.empty();

        // Lista Normal
        listaOperaciones.stream().sorted(Comparator.comparing(OperacionDTO::getId).reversed()).forEach( e -> {
            LOGGER.info("Elemento Normal : {}", e);
        });

        optionalList = Optional.of(listaOperaciones);

        optionalList.ifPresent(lista -> lista.forEach( e ->
                LOGGER.info("Element Optional : {}", e)
        ));

        return optionalList;
    }

}
