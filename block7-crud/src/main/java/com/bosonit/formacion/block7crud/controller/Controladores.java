package com.bosonit.formacion.block7crud.controller;

import com.bosonit.formacion.block7crud.application.PersonaServiceslmpl;
import com.bosonit.formacion.block7crud.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class Controladores {
    @Autowired
    PersonaServiceslmpl personaServices;
    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona (@RequestBody PersonaInputDto persona){
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaServices.addPersona(persona));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersonaById (@RequestBody PersonaInputDto persona,
                                                               @PathVariable int id){
        try {
            personaServices.getPersonaById(id);
            return ResponseEntity.ok().body(personaServices.updatePersonaById(persona,id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersonaId (@PathVariable int id){
        try {
            return ResponseEntity.status(202).body(personaServices.deletePersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById (@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personaServices.getPersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/nombre/{name}")
    public Iterable<PersonaOutputDto> getPersonaByName (@PathVariable String name,
                                                              @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                              @RequestParam(defaultValue = "4", required = false) int pageSize){
            return personaServices.getPersonaByName(name, pageNumber, pageSize);
    }
    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersona (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize){
        return personaServices.getAllPersona(pageNumber, pageSize);
    }
}
