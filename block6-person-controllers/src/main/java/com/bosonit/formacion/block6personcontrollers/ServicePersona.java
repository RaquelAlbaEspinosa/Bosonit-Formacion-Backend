package com.bosonit.formacion.block6personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class ServicePersona {
    Persona user = new Persona();
    public Persona createPersona(String nombre, String poblacion, Integer edad){
        this.user.setEdad(edad);
        this.user.setNombre(nombre);
        this.user.setPoblacion(poblacion);
        return user;
    }
}
