package com.learning.java8.example.app.models.dto;

public class OperacionDTO {

    private long id;

    private String abreviatura;

    private String descripcion;


    public OperacionDTO() {
    }

    public OperacionDTO(long id, String abreviatura, String descripcion) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "OperacionDTO{" +
                "id=" + id +
                ", abreviatura='" + abreviatura + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
