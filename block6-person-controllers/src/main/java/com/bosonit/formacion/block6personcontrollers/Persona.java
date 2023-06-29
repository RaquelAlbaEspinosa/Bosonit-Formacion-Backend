package com.bosonit.formacion.block6personcontrollers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {
    String nombre;
    String poblacion;
    Integer edad;
    public Persona (){}
    public Persona(@JsonProperty("poblacion") String poblacion,
                   @JsonProperty("nombre") String nombre,
                   @JsonProperty("edad") Integer edad){
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.edad = edad;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", edad=" + edad +
                '}';
    }
}
