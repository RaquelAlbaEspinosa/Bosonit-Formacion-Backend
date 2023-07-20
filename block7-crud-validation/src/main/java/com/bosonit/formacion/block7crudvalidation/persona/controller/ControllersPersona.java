package com.bosonit.formacion.block7crudvalidation.persona.controller;

import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.feign.ProfesorFeign;
import com.bosonit.formacion.block7crudvalidation.persona.application.PersonaServices;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllersPersona {
    private String simple = "simple";
    private String full = "full";
    @Autowired
    PersonaServices personaServices;
    @Autowired
    ProfesorFeign profesorFeign;
    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona (@RequestBody PersonaInputDto persona){
        URI location = URI.create("/persona");
            return ResponseEntity.created(location).body(personaServices.addPersona(persona));
    }
    @GetMapping("/{id}")
    public ResponseEntity getPersonaById (@PathVariable int id,
                                          @RequestParam(defaultValue = "simple", required = false)
                                          String outputType){
        PersonaOutputDto persona = personaServices.getPersonaById(id);
        ResponseEntity response = ResponseEntity.ok().body(persona);
        if(outputType.equals(this.simple)){
            response = ResponseEntity.ok().body(persona);
        } else if (outputType.equals(this.full)){
            if (personaServices.getTypeOfPersona(persona.getIdPersona()).equals("Estudiante")) {
                response = ResponseEntity.ok()
                        .body(personaServices.getPersonaByIdEstudiante(id));
            } else if(personaServices.getTypeOfPersona(persona.getIdPersona()).equals("Profesor")){
                response = ResponseEntity.ok()
                        .body(personaServices.getPersonaByIdProfesor(id));
            }
        } else {
            response = ResponseEntity.badRequest()
                    .body("El outputType debe ser 'simple' o 'full'");
        }
        return response;
    }
    @GetMapping("/usuario/{usuario}")
    public Iterable getPersonaByUsuario (@PathVariable String usuario,
                                         @RequestParam(defaultValue = "simple", required = false)
                                         String outputType){
        Iterable personas = null;
        if(outputType.equals(this.simple)){
            personas = personaServices.getPersonaByUsuario(usuario);
        } else if (outputType.equals(this.full)) {
            personas = personaServices.getPersonaByUsuarioFull(usuario);
        }
        return personas;
    }
    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping
    public Iterable getAllPersona (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                   @RequestParam(defaultValue = "4", required = false) int pageSize,
                                   @RequestParam(defaultValue = "simple", required = false) String outputType) {
        Iterable personas = null;
        if(outputType.equals(this.simple)){
            personas = personaServices.getAllPersona(pageNumber, pageSize);
        } else if (outputType.equals(this.full)) {
            personas = personaServices.getAllPersonaFull(pageNumber, pageSize);
        }
        return personas;
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona (@RequestBody PersonaInputDto persona, @PathVariable int id){
        try {
            personaServices.getPersonaById(id);
            return ResponseEntity.ok().body(personaServices.updatePersona(persona, id));
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona (@PathVariable int id){
        try {
            personaServices.getPersonaById(id);
            personaServices.deletePersona(id);
            return ResponseEntity.ok().body("Se ha borrado a la persona con id: " + id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @GetMapping("/profesor/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById (@PathVariable String id,
                                                              @RequestParam(defaultValue = "simple", required = false) String outputType){
        return profesorFeign.getProfesorById(id, outputType);
    }
}
