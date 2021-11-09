package com.learning.java8.example.app.defaultmethods.interfaces;

public interface DefaultMethod {

    void caminar();

    // Using Java 8
    default String trotar() {
        return "Empezando a trotar...";
    }

}
