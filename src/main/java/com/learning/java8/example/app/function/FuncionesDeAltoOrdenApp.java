package com.learning.java8.example.app.function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FuncionesDeAltoOrdenApp {

    //private static final Log LOG = LogFactory.getLog(FuncionesDeAltoOrdenApp.class);
    private static final Logger LOGGER = LogManager.getLogger(FuncionesDeAltoOrdenApp.class);


    // FUNCTIONS

    /**
     * Function <Tipo de Entrada, Tipo de Salida>
     */
    private final Function<String, String> convertirMayusculas = x -> x.toUpperCase();

    private final Function<String, String> convertirMinusculas = String::toLowerCase;



    // MAIN
    public static void main(String[] args) {
        FuncionesDeAltoOrdenApp tester = new FuncionesDeAltoOrdenApp();

        tester.process();
    }



    // METHODS

    public void process() {
        String texto = "Texto de Prueba.";

        LOGGER.info(">>> Usando una Funcion como Parámetro...");
        // Convertir un texto a Mayúsculas
        LOGGER.info("> Convertir texto a Mayúsculas.");
        setConvertirTexto(convertirMayusculas, texto);

        // Convertir un texto a Minúsculas
        LOGGER.info("> Convertir texto a Minúsculas.");
        setConvertirTexto(convertirMinusculas, texto);



        LOGGER.info(">>> Usando una Funcion como Retorno...");
        String respuesta = mostrar("Hola ").apply("Abraham");
        LOGGER.info("Respuesta : {}", respuesta);

        Double valueDouble = convertStringToLong().apply(10);
        LOGGER.info("Valor Double : {}", valueDouble);

        List<String> listNames = new ArrayList<>();
        listNames.add("Yelena");
        listNames.add("Dayana");
        listNames.add("Abraham");
        listNames.add("Diana");

        filtrar(listNames, print(), 6, "na");
    }

    /**
     * Método utilizado para hacer la conversión de un texto.
     *
     * @param function Función que se realizará al parámetro recibido
     * @param texto Texto a procesar.
     */
    private void setConvertirTexto(Function<String, String> function, String texto) {

        LOGGER.info("Texto Convertido : {}", function.apply(texto));
    }

    /**
     * Método utilizado para retornar una cadena concatenada.
     *
     * @param value Cadena Recibida.
     * @return Función.
     */
    private Function<String, String> mostrar(String value) {
        return (String x) -> value + x;
    }

    /**
     * Método utilizado para convertir una cadena a un tipo Long.
     *
     * @return Función de conversión.
     */
    private Function<Integer, Double> convertStringToLong() {
        return d -> Double.valueOf(d);
    }

    public void filtrar(List<String> list, Consumer<String> consumer, int longitud, String value) {
        LOGGER.info("Using Filter : Máximo de Longitud -> {}", longitud);
        list.stream().filter(filterMaximaLongitud(longitud)).forEach(consumer);


        LOGGER.info("Using Filter : Contiene Elemento -> {}", value);
        list.stream().filter(filterContieneDato(value)).forEach(consumer);
    }

    /**
     * Predicado auxiliar utilizado para validar la longitud máxima para cada argumento.
     *
     * Nota: Un predicado describe la acción (con valor booleano) que se realiza al sujeto (argumento) o lo que se dice
     * sobre él, es decir, se cumple o no.
     *
     * @param longitud Longitud Máxima.
     * @return Predicado para cada argumento.
     */
    public Predicate<String> filterMaximaLongitud(int longitud) {
        return s -> s.length() <= longitud;
    }

    /**
     * Predicado auxiliar utilizado para validar que cada argumento contenga el valor recibido como parámetro.
     *
     * Nota: Un predicado describe la acción (con valor booleano) que se realiza al sujeto (argumento) o lo que se dice
     * sobre él, es decir, se cumple o no.
     *
     * @param valor Valor recibido para validar.
     * @return Predicado para cada argumento.
     */
    public Predicate<String> filterContieneDato(String valor) {
        return s -> s.contains(valor);
    }

    /**
     * Consumidor auxiliar utilizado para hacer la impresión de cada argumento.
     *
     * Nota: Representa una operación (acción a realizar para cada argumento) que acepta un solo argumento de entrada y
     * no devuelve resultado.
     *
     * @return Accción para cada argumento.
     */
    public Consumer<String> print() {
        return s -> LOGGER.info("Elemento : {}", s);
    }

}
