package com.learning.java8.example.app.methodreferences;

import com.learning.java8.example.app.methodreferences.dto.PersonaDTO;
import com.learning.java8.example.app.methodreferences.interfaces.Persona;
import com.learning.java8.example.app.methodreferences.interfaces.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Las referencias a métodos ayudan a señalar los métodos por sus nombres. Una referencia a un método se describe
 * mediante el símbolo "::".
 */
public class TestMethodReferences {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMethodReferences.class);



    // MAIN.

    public static void main(String[] args) {

        TestMethodReferences tester = new TestMethodReferences();

        // Referencia a un método estático.
        tester.imprimir();


        // Referencia a un método mediante una instancia.
        tester.referenciarMetodoInstanciaObjetoArbitrario();


        // A la interfaz funcional la implementamos con el método de la derecha.
        // Es decir, el método se le pasa como parámetro a la interfaz.
        Printer printer = tester::referenciarMetodoInstanciaObjetoParticular;
        printer.print();

        // Referencia a un Constructor.
        tester.referenciarConstructor();
    }



    // MÉTODOS.

    public void imprimir() {

        // Using Lambda Expression
        //Printer printer = () -> TestMethodReferences.referenciarMetodoStatic();

        // Using Method References
        Printer printer = TestMethodReferences::referenciarMetodoStatic;

        LOGGER.info(printer.start());
        printer.print();
        LOGGER.info(printer.end());
    }

    public static void referenciarMetodoStatic() {
        LOGGER.info("Método Referenciado Static.");
    }

    public void referenciarMetodoInstanciaObjetoArbitrario() {
        String [] arrayNombres = {"Yelena", "Abraham", "Diana", "Dayana"};

        // Using Java 7: anonymous class
        /*Arrays.sort(arrayNombres, new Comparator<String>() {
            @Override
            public int compare(String valueOne, String valueTwo) {
                return valueOne.compareToIgnoreCase(valueTwo);
            }
        });*/

        // Using Java 8: Lambda Expression
        //Arrays.sort(arrayNombres, (x, y) -> x.compareToIgnoreCase(y));

        // Using Java 8: Method References
        Arrays.sort(arrayNombres, String::compareToIgnoreCase);

        LOGGER.info("Lista de Nombres: {}", Arrays.toString(arrayNombres));
    }

    public void referenciarMetodoInstanciaObjetoParticular() {
        LOGGER.info("Método Referenciado, Instancia de un Objeto en Particular.");
    }

    public void referenciarConstructor() {

        // Using Java 7: anonymous class
        /*Persona persona = new Persona() {
            @Override
            public PersonaDTO crear(int identificador, String nombre) {
                return new PersonaDTO(identificador, nombre);
            }
        };*/

        // Using Java 8: Lambda Expression
        //Persona persona = (int x, String y) -> new PersonaDTO(x, y);

        // Using Java 8: Method References
        Persona persona = PersonaDTO::new;

        PersonaDTO personaDTO = persona.crear(1, "Abraham");
        LOGGER.info("Persona creada : {}", personaDTO.toString());
    }

}
