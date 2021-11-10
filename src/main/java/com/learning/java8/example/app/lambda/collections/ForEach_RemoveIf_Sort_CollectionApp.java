package com.learning.java8.example.app.lambda.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ForEach_RemoveIf_Sort_CollectionApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForEach_RemoveIf_Sort_CollectionApp.class);


    private List<String> listNames;


    // MAIN.
    public static void main(String[] args) {
        ForEach_RemoveIf_Sort_CollectionApp tester = new ForEach_RemoveIf_Sort_CollectionApp();

        tester.procesar();
    }



    // MÉTODOS.

    public void procesar() {
        listNames = new ArrayList<String>();
        listNames.add("Yelena");
        listNames.add("Dayana");
        listNames.add("Abraham");
        listNames.add("Diana");

        usingForEach();
        usingSort();
        usingRemoveIf();

        LOGGER.info("Lista : {}", listNames.toString());
    }

    public void usingForEach() {

        // Using Java 7
        /*for (String element : list) {
            LOGGER.info(element);
        }*/

        // Using Java 8: Lambda Expression
        /*listNames.forEach(element -> LOGGER.info("Nombre : {}", element));*/

        // Using Java 8: Method References
        listNames.forEach(LOGGER::info);
    }

    public void usingRemoveIf() {

        // Using Java 7
        /*Iterator<String> iterator = listNames.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase("Abraham")) {
                iterator.remove();
            }
        }*/

        // Using Java 8: Lambda Expression
        listNames.removeIf(element -> element.equalsIgnoreCase("Abraham"));
    }

    public void usingSort() {

        // Using Java 7
        /*Collections.sort(listNames, new Comparator<String>() {
            @Override
            public int compare(String valueOne, String valueTwo) {
                return valueOne.compareToIgnoreCase(valueTwo);
            }
        });*/


        // Using Java 8: Lambda Expression
        /*Collections.sort(listNames, (elementOne, elementTwo) -> elementOne.compareToIgnoreCase(elementTwo));*/
        // or
        /*listNames.sort((x, y) -> x.compareToIgnoreCase(y));*/


        // Using Java 8: Method References
        listNames.sort(String::compareToIgnoreCase);

        LOGGER.info("Lista Ordenada: {}", listNames.toString());
    }

}
