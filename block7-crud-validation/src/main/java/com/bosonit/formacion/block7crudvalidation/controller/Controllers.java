package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.application.PersonaServiceslmpl;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.error.CustomError;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/persona")
public class Controllers {
    @Autowired
    PersonaServiceslmpl personaServices;
    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona (@RequestBody PersonaInputDto persona){
        URI location = URI.create("/persona");
            return ResponseEntity.created(location).body(personaServices.addPersona(persona));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById (@PathVariable int id){
        personaServices.getPersonaById(id);
        return ResponseEntity.ok().body(personaServices.getPersonaById(id));
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
