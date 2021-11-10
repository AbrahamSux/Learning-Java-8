package com.learning.java8.example.app.functionalinterface;

import com.learning.java8.example.app.functionalinterface.interfaces.Operacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFunctionalInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestFunctionalInterface.class);



    // MAIN.

    public static void main(String[] args) {

        TestFunctionalInterface tester = new TestFunctionalInterface();

        double result = tester.realizarOperacion(7, 5);
        LOGGER.info("Resultado de la operación : {}", result);
    }



    // MÉTODOS.

    double realizarOperacion(double numOne, double numTwo) {

        Operacion operacion = (x, y) -> x + y;

        return operacion.calcular(numOne, numTwo);
    }

}
