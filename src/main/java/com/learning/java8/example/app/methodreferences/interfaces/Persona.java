package com.learning.java8.example.app.methodreferences.interfaces;

import com.learning.java8.example.app.methodreferences.dto.PersonaDTO;

@FunctionalInterface
public interface Persona {

    PersonaDTO crear(int identificador, String nombre);

}
