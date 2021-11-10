package com.learning.java8.example.app.methodreferences.dto;

public class PersonaDTO {

    private int idPersona;

    private String nombre;


    public PersonaDTO() {
    }

    public PersonaDTO(int idPersona, String nombre) {
        this.idPersona = idPersona;
        this.nombre = nombre;
    }


    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
