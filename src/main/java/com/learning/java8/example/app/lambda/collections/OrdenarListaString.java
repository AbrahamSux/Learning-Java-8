package com.learning.java8.example.app.lambda.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenarListaString {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdenarListaString.class);

    public static void main(String[] args) {

        List<String> listaPaises = new ArrayList<>();
        listaPaises.add("Peru");
        listaPaises.add("Salvador");
        listaPaises.add("Ecuador");
        listaPaises.add("Honduras");
        listaPaises.add("Mexico");


        // Using Java 7
        /*listaPaises.sort(new Comparator<String>() {
            @Override
            public int compare(String object1, String object2) {
                return object1.compareTo(object2);
            }
        });*/

        // Using Java 8
        listaPaises.sort(String::compareTo);

        for (String pais : listaPaises) {
            LOGGER.info("Pais : {}", pais);
        }
    }

}
