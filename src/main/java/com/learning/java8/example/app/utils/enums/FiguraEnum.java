package com.learning.java8.example.app.utils.enums;

public enum FiguraEnum {

    CUADRADO {

        @Override
        public int getLados() {
            return 4;
        }

        @Override
        public String getSaludo() {
            return "Hola, soy un Cuadrado!";
        }

    },
    RECTANGULO {

        @Override
        public int getLados() {
            return 4;
        }

        @Override
        public String getSaludo() {
            return "Hola, soy un Rectángulo!";
        }

    },
    TRIANGULO {

        @Override
        public int getLados() {
            return 3;
        }

        @Override
        public String getSaludo() {
            return "Hola, soy un Triángulo!";
        }

    };



    public abstract int getLados();

    public abstract String getSaludo();

}
