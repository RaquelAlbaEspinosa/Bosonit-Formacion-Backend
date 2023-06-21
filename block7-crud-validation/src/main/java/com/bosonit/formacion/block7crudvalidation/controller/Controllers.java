package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServiceslmpl;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class Controllers {
    @Autowired
    PersonaServiceslmpl personaServices;
    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById (@PathVariable int id){
        try {
            personaServices.getPersonaById(id);
            return ResponseEntity.ok().body(personaServices.getPersonaById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/usuario/{usuario}")
    public Iterable<PersonaOutputDto> getPersonaByUsuario (@PathVariable String usuario){
        return personaServices.getPersonaByUsuario(usuario);
    }
    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersona (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaServices.getAllPersona(pageNumber, pageSize);
    }
}
