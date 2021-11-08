package com.learning.java8.example.app.lambda.calculos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMathOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMathOperation.class);


    // ATRIBUTOS DE LA CLASE.
    private static double numOne = 10;
    private static double numTwo = 5;



    // MÉTODOS.

    public static void main(String[] args) {
        TestMathOperation tester = new TestMathOperation();


        // Con declaración de tipo.
        MathOperation addition = Double::sum;

        // Sin declaración de tipo.
        MathOperation subtraction = (a, b) -> a - b;

        // Con declaración de devolución junto con llaves.
        MathOperation multiplication = (double a, double b) -> { return a * b; };

        // Sin declaración de devolución y sin llaves.
        MathOperation division = (double a, double b) -> a / b;


        LOGGER.info("{} + {} = {}", numOne, numTwo, tester.operate(numOne, numTwo, addition));
        LOGGER.info("{} - {} = {}", numOne, numTwo, tester.operate(numOne, numTwo, subtraction));
        LOGGER.info("{} * {} = {}", numOne, numTwo, tester.operate(numOne, numTwo, multiplication));
        LOGGER.info("{} / {} = {}", numOne, numTwo, tester.operate(numOne, numTwo, division));
    }

    /**
     * Método auxiliar utilizado para realizar una operacion con los datos recibidos.
     *
     * @param a Valor a evaluar.
     * @param b Valor a evaluar.
     * @param mathOperation Tipo de operación a realizar.
     * @return El Resultado de la operación realizada.
     */
    private double operate(double a, double b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }



    // INTERFACES.

    interface MathOperation {
        double operation(double valueOne, double valueTwo);
    }

}
