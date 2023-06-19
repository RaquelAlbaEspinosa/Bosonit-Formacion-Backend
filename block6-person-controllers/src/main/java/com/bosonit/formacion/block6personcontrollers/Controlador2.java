package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    Persona persona;
    List<Ciudad> ciudades;
    @Autowired
    public Controlador2 (ServicePersona servicePersona, ServiceCiudad ciudadService){
        this.persona = servicePersona.user;
        this.ciudades = ciudadService.ciudades;
    }
    @GetMapping("/getPersona")
    public Persona getPersona (){
        this.persona.setPoblacion(persona.getPoblacion());
        this.persona.setNombre(persona.getNombre());
        this.persona.setEdad(persona.getEdad() * 2);
        return persona;
    }
    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades (){
        return this.ciudades;
    }
}
