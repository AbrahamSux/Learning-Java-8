package com.learning.java8.example.app.optional;

import com.learning.java8.example.app.models.dto.OperacionDTO;
import com.learning.java8.example.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OptionalStreamApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalStreamApp.class);


    List<OperacionDTO> listaOperaciones;



    // MAIN

    public static void main(String[] args) {
        OptionalStreamApp tester = new OptionalStreamApp();

        LOGGER.info(">>> Usando List<Optional<Object>>... ");
        List<Optional<OperacionDTO>> listOptional = tester.usingListOptional();
        if (listOptional != null && !listOptional.isEmpty()) {
            LOGGER.info("Lista de Elementos Opcionales : {}", listOptional);
        }


        LOGGER.info(">>> Usando Optional<List<OperacionDTO>>... ");
        Optional<List<OperacionDTO>> optionalList = tester.usingOptionalList();
        if (optionalList.isPresent()) {
            LOGGER.info("Optional List : {}", optionalList);
        }


        LOGGER.info(">>> Usando List<Optional<Object>> & Filter...");
        tester.usingListOptionalFilter();
    }



    // METHODS

    public OptionalStreamApp() {
        listaOperaciones = new ArrayList<>(Constants.listOfOperations);
    }

    /**
     * Ejemplo del uso de una Lista de Optional.
     *
     * @return List<Optional<OperacionDTO>>
     */
    public List<Optional<OperacionDTO>> usingListOptional() {

        List<Optional<OperacionDTO>> optional = new ArrayList<>();

        // Lista Normal
        listaOperaciones.stream().sorted(Comparator.comparing(OperacionDTO::getId).reversed()).forEach( e -> {
            // LOGGER.info("Elemento Normal : {}", e);

            optional.add(Optional.of(e));
        });


        // Lista de Optional
        // Opción 1
        /*if (!optional.isEmpty()) {
            optional.forEach( e -> LOGGER.info("Element Optional : {}", e));
        }*/

        // Opción 2
        optional.forEach(e -> {
            if (e.isPresent()) {
                LOGGER.info("Element Optional : {}", e);
            }
        });

        return optional;
    }

    /**
     * Ejemplo del uso de un Optional con una Lista.
     *
     * @return Optional<List<OperacionDTO>>
     */
    public Optional<List<OperacionDTO>> usingOptionalList() {

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

    public List<Optional<OperacionDTO>> usingListOptionalFilter() {

        List<Optional<OperacionDTO>> optional = new ArrayList<>();

        // Lista Normal
        listaOperaciones.stream().sorted(Comparator.comparing(OperacionDTO::getId).reversed()).forEach(e ->
                optional.add(Optional.of(e))
        );

        optional.add(Optional.empty());
        // optional.add(null);

        // Imprimir Lista Optional: Opción 1 (Imprime también el valor vacío)
        /*optional.forEach(e -> LOGGER.info("Element Optional : {}", e));*/

        // Imprimir Lista Optional: Opción 2
        /*optional.stream().filter(Optional::isPresent).forEach(e -> LOGGER.info("Element Optional : {}", e.get()) );*/

        // Imprimir Lista Optional: Opción 3
        optional.stream().filter(Optional::isPresent).map(Optional::get).forEach(e -> LOGGER.info("Element Optional : {}", e) );

        return optional;
    }
}
