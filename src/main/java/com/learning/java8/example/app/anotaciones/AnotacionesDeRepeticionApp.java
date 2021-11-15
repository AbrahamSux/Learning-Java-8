package com.learning.java8.example.app.anotaciones;

import com.learning.java8.example.app.anotaciones.anotacion.Lenguaje;
import com.learning.java8.example.app.anotaciones.anotacion.Lenguajes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class AnotacionesDeRepeticionApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnotacionesDeRepeticionApp.class);



    // MAIN
    public static void main(String[] args) {
        AnotacionesDeRepeticionApp tester = new AnotacionesDeRepeticionApp();

        tester.procesar();
    }



    // METHODS

    public void procesar() {

        // Example 1
        LOGGER.info(">>> Using GetAnnotationsByType...");
        Lenguaje[] arrayLenguajes = LenguajesDeProgramacion.class.getAnnotationsByType(Lenguaje.class);

        LOGGER.info("Número de Lenguajes de Programación : {}", arrayLenguajes.length);

        // Print using java 8
        Arrays.stream(arrayLenguajes).forEach( l -> LOGGER.info("Lenguaje {}", l.value()));



        // Example 2
        LOGGER.info(">>> Using GetAnnotation...");
        Lenguajes lenguajes = LenguajesDeProgramacion.class.getAnnotation(Lenguajes.class);

        LOGGER.info("Número de Leguajes de Programación : {}", lenguajes.value().length);

        // Option 1 : print using java 8
        Arrays.stream(lenguajes.value()).forEach( l -> LOGGER.info("Lenguaje : \"{}\", Versión {}", l.value(), l.version()));

        // Opcion 2 : print using java 7
        /*for (Lenguaje lenguaje : lenguajes.value()) {
            LOGGER.info("Lenguaje : {}", lenguaje.value());
        }*/
    }

}



// INTERFACES

// Using Java 7
/*@Lenguajes({
        @Lenguaje("Java"),
        @Lenguaje("JavaScript"),
        @Lenguaje("Python")
})*/
// Using Java 8
@Lenguaje(value = "Java", version = 1.8)
@Lenguaje("JavaScript")
@Lenguaje(value = "Python", version = 3.4)
interface LenguajesDeProgramacion {

}
