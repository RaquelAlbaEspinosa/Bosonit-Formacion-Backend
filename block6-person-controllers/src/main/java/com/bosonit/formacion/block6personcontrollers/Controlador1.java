package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private ServicePersona personaService;
    private ServiceCiudad ciudadService;
    @Autowired
    public Controlador1(ServicePersona personaService, ServiceCiudad ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }
    @GetMapping("/addPersona")
    public ResponseEntity<Persona> addPersona (
        @RequestHeader("nombre") String nombre,
                @RequestHeader("poblacion") String poblacion,
                        @RequestHeader("edad") Integer edad) {
        Persona user = personaService.createPersona(nombre, poblacion, edad);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/addCiudad")
    public ResponseEntity<Ciudad> addCiudad (@RequestHeader("nombre") String nombre,
                           @RequestHeader("numeroHabitantes") int numeroHabitantes){
        Ciudad city = new Ciudad(nombre, numeroHabitantes);
        ciudadService.addCiudad(city);
        return ResponseEntity.ok(city);
    }
}
