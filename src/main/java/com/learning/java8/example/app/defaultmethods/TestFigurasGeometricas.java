package com.learning.java8.example.app.defaultmethods;

import com.learning.java8.example.app.defaultmethods.enums.FiguraEnum;
import com.learning.java8.example.app.defaultmethods.interfaces.Figura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFigurasGeometricas {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestFigurasGeometricas.class);



    // MAIN.

    public static void main(String[] args) {

        Triangulo triangulo = new FigurasGeometricas();
        LOGGER.info(triangulo.soy(FiguraEnum.TRIANGULO));
        LOGGER.info("Lados del Triángulo: {}", triangulo.lados(FiguraEnum.TRIANGULO));

        Rectangulo rectangulo = new FigurasGeometricas();
        LOGGER.info(rectangulo.soy(FiguraEnum.RECTANGULO));
        LOGGER.info("Lados del Rectángulo: {}", rectangulo.lados(FiguraEnum.RECTANGULO));

        Cuadrado cuadrado = new FigurasGeometricas();
        LOGGER.info(cuadrado.soy(FiguraEnum.CUADRADO));
        LOGGER.info("Lados del Cuadrado: {}", cuadrado.lados(FiguraEnum.CUADRADO));


        LOGGER.info(">>>");


        Rectangulo figuraRectangulo = new FigurasGeometricasDeCuatroLados();
        LOGGER.info(figuraRectangulo.soy(FiguraEnum.RECTANGULO));
        LOGGER.info("Y tengo {} lados iguales!", figuraRectangulo.lados(FiguraEnum.RECTANGULO));

        Cuadrado figuraCuadrado = new FigurasGeometricasDeCuatroLados();
        LOGGER.info(figuraCuadrado.soy(FiguraEnum.CUADRADO));
        LOGGER.info("Y tengo {} lados iguales!", figuraCuadrado.lados(FiguraEnum.CUADRADO));

    }



    // CLASES ANIDADAS ESTÁTICAS.

    static class FigurasGeometricas implements Cuadrado, Triangulo, Rectangulo {


        // MÉTODOS.

        @Override
        public String soy(FiguraEnum tipoFigura) {

            return tipoFigura.getSaludo();
        }

        @Override
        public int lados(FiguraEnum tipoFigura) {
            // System.out.println(">>> lados( { " + tipoFigura + " } )");

            return tipoFigura.getLados();
        }

    }

}

// CLASES EXTERNAS

class FigurasGeometricasDeCuatroLados implements Rectangulo, Cuadrado {


    // MÉTODOS.

    @Override
    public String soy(FiguraEnum tipoFigura) {
        return tipoFigura.getSaludo();
    }

    /**
     * Método sobrescrito para saber cuantos lados iguales tiene la figura de cuatro lados.
     *
     * @param tipoFigura Tipo de la Figura de 4 lados.
     * @return Lados iguales de la figura.
     */
    @Override
    public int lados(FiguraEnum tipoFigura) {

        int lados = 0;

        switch (tipoFigura) {
            case CUADRADO:
                lados = 4;
                break;
            case RECTANGULO:
                lados = 2;
                break;
            default: return 0;
        }

        return lados;
    }

}



// INTERFACES.

interface Cuadrado extends Figura {

    default int lados(FiguraEnum tipoFigura) {
        System.out.println(">>> Cuadrado.lados( { " + tipoFigura + " } )");

        return tipoFigura.getLados();
    }
}

interface Triangulo extends Figura {

    default int lados(FiguraEnum tipoFigura) {
        System.out.println(">>> Triangulo.lados( { " + tipoFigura + " } )");

        return tipoFigura.getLados();
    }

}

interface Rectangulo extends Figura {

    default int lados(FiguraEnum tipoFigura) {
        System.out.println(">>> Rectangulo.lados( { " + tipoFigura + " } )");

        return tipoFigura.getLados();
    }

}
