package com.learning.java8.example.app.lambda.map;

import com.learning.java8.example.app.models.dto.OperacionDTO;
import com.learning.java8.example.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapApp.class);


    private Map<Integer, String> map;



    // MAIN

    public static void main(String[] args) {
        MapApp tester = new MapApp();

        tester.process();
    }



    // METHODS

    public void process() {
        map = new HashMap<>();
        map.put(1, "Yelena");
        map.put(2, "Diana");
        map.put(3, "Dayana");
        map.put(4, "Abraham");

        // Se inserta un valor si éste no está en el Map.
        insertarSiEsAusente();


        LOGGER.info(">>> Using Java 7...");
        printUsingJava7();

        LOGGER.info(">>> Using Java 8...");
        printusingJava8();

        LOGGER.info(">>> Using ComputeIfPresent...");
        operarSiEstaPresente();

        LOGGER.info(">>> Using GetOrDefault...");
        getValuesOrDefault();

        LOGGER.info(">>> Using Collectors.toMap(k, v)...");
        recolectar();
    }

    public void printUsingJava7() {
        for (Map.Entry<Integer, String> value : map.entrySet()) {
            LOGGER.info("Elemento : K( {} ) -> V( {} )", value.getKey(), value.getValue());
        }
    }

    public void printusingJava8() {
        // Opción 1
        /*map.forEach((k, v) -> LOGGER.info("Elemento : K( {} ) -> V( {} )", k, v));*/


        // Opción 2 : Imprimiendo y ordenando por valor.
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(m ->
                LOGGER.info("Elemento : K( {} ) -> V( {} )", m.getKey(), m.getValue())
        );
    }

    public void recolectar() {
        Map<Integer, String> mapaRecolectado = map.entrySet().stream()
                .filter(m -> m.getValue().contains("na"))
                //.collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        mapaRecolectado.forEach((k, v) -> LOGGER.info("Elemento : K( {} ) -> V( {} )", k, v));
    }

    public void insertarSiEsAusente() {
        // Si el Key no existe lo agrega
        map.putIfAbsent(5, "Denisse");

        // caso contrario, si ya está no lo agrega.
        map.putIfAbsent(3, "Aurora");
    }

    public void operarSiEstaPresente() {
        // Example 1
        map.computeIfPresent(4, (k, v) -> "Nombre del key 4 : ".concat(v) );
        LOGGER.info( map.get(4) );

        // Example 2
        Map<Integer, OperacionDTO> mapOperaciones = new HashMap<>();
        Constants.listOfOperations.forEach(operacion -> {
            mapOperaciones.put((int) operacion.getId(), operacion);
        });
        mapOperaciones.computeIfPresent(3, (k, v) -> new OperacionDTO() );
        LOGGER.info("Operación : {}", mapOperaciones.get(3));
    }

    public void getValuesOrDefault() {
        final int key = 18;

        if (map.containsKey(key)) {
            LOGGER.info("Valor Obtenido : {}", map.get(key));
        } else {
            String value = map.getOrDefault(key, "Default value");
            LOGGER.info("Valor Predeterminado : {}", value);
        }

    }

}
