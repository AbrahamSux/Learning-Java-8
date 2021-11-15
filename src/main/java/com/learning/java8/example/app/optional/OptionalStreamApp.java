package com.learning.java8.example.app.optional;

import com.learning.java8.example.app.models.dto.OperacionDTO;
import com.learning.java8.example.app.models.entity.Person;
import com.learning.java8.example.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


        LOGGER.info(">>> Misuse of Optionals...");
        List<Person> personList = Arrays.asList(
                new Person("Jorge", 42, "12345"),
                new Person("José", 39, "12345"),
                new Person("Arturo", 37, "12345"),
                new Person("Jorge", 38, "12345"),
                new Person("Ricardo", 38, "12345")
        );
        //List<Person> persons = tester.doSearch(personList, "Jorge", null);
        List<Person> persons = tester.search(personList, "Jorge");
        //List<Person> persons = tester.search(personList, "Jorge", 35);
        LOGGER.info("Lista de usuarios con nombre Jorge : {}", persons);
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



    /**
     * El uso indebido de Opcionals.
     *
     * Opcional está destinado a usarse como un tipo de retorno. No se recomienda intentar usarlo como un tipo de campo.
     */
    public List<Person> search(List<Person> people, String name) {
        return doSearch(people, name, Optional.empty());
    }

    public List<Person> search(List<Person> people, String name, int age) {
        return doSearch(people, name, Optional.of(age));
    }

    public List<Person> doSearch(List<Person> people, String name, Optional<Integer> age) { // Optional: No recomendado usarlo como parámetro.
        LOGGER.info(">> doSearch ( list, {}, {} )", name, age);

        // Opción 1 : Validación de 'age' en caso de ser Nulo o Vacío.
        final Integer ageFilter = Optional.ofNullable(age).isPresent() ? age.orElse(0) : 0;

        // Opción 2
        /*final Integer ageFilter = Stream.<Supplier<Optional<Integer>>>of(() -> age)
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .map(Optional::get)
                .findFirst()
                .orElse(0);*/

        LOGGER.info("Edad : {}", ageFilter);


        // Null checks for people and name
        return people.stream()
                .filter(p -> p.getName().isPresent() && p.getAge().isPresent())
                .filter(p -> p.getName().get().equals(name))
                .filter(p -> p.getAge().get() >= ageFilter)
                .collect(Collectors.toList());
    }

}
