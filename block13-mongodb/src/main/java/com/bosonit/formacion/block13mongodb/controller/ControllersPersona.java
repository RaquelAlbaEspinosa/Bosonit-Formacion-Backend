package com.bosonit.formacion.block13mongodb.controller;

import com.bosonit.formacion.block13mongodb.application.PersonaServices;
import com.bosonit.formacion.block13mongodb.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13mongodb.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllersPersona {
    @Autowired
    PersonaServices personaServices;
    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona (@RequestBody PersonaInputDto persona){
        URI location = URI.create("/persona");
            return ResponseEntity.created(location).body(personaServices.addPersona(persona));
    }
    @GetMapping("/{id}")
    public ResponseEntity getPersonaById (@PathVariable String id){
        PersonaOutputDto persona = personaServices.getPersonaById(id);
        ResponseEntity response = ResponseEntity.ok().body(persona);
        return response;
    }
    @GetMapping
    public Iterable getAllPersona (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                   @RequestParam(defaultValue = "10", required = false) int pageSize) {
            return personaServices.getAllPersona(pageNumber, pageSize);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona (@RequestBody PersonaInputDto persona, @PathVariable String id){
            personaServices.getPersonaById(id);
            return ResponseEntity.ok().body(personaServices.updatePersona(persona, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona (@PathVariable String id){
            personaServices.getPersonaById(id);
            personaServices.deletePersona(id);
            return ResponseEntity.ok().body("Se ha borrado a la persona con id: " + id);
    }
}
