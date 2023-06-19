package com.bosonit.formacion.block6simplecontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller")
public class Controller {
    @GetMapping("/user/{nombre}")
    public String saludo(@PathVariable String nombre){
        return "Hola " + nombre;
    }
    @PostMapping("/useradd")
    public Persona incrementarEdad (@RequestBody String user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Persona persona = objectMapper.readValue(user, Persona.class);
            Persona userPersona = new Persona(persona.getNombre(), persona.getPoblacion(), persona.getEdad() + 1);
            return userPersona;
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
