package com.bosonit.formacion.block7crudvalidation.profesor.controller;

import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.profesor.application.ProfesorService;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/profesor")
public class ControllersProfesor {
    @Autowired
    ProfesorService profesorService;
    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesor (@Valid @RequestBody ProfesorInputDto profesor){
        URI location = URI.create("/profesor");
        try {
            return ResponseEntity.created(location).body(profesorService.addProfesor(profesor));
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getProfesorById (@PathVariable String id,
                                           @RequestParam(defaultValue =  "simple", required = false)
                                           String outputType){
        if(outputType.equals("simple")) {
            return ResponseEntity.ok().body(profesorService.getProfesorById(id));
        } else if (outputType.equals("full")) {
            return ResponseEntity.ok().body(profesorService.getProfesorByIdFull(id));
        } else {
            return ResponseEntity.unprocessableEntity().body("outputType debe ser 'simple' o 'full'");
        }
    }
    @GetMapping("/usuario/{usuario}")
    public Iterable<ProfesorOutputDto> getProfesorByUsuario (@PathVariable String usuario){
        return profesorService.getProfesorByUsuario(usuario);
    }
    @GetMapping
    public Iterable<ProfesorOutputDto> getAllProfesor (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return profesorService.getAllProfesor(pageNumber, pageSize);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> updateProfesor (@Valid @RequestBody ProfesorInputDto profesor,
                                                             @PathVariable String id){
        profesorService.getProfesorById(id);
        return ResponseEntity.ok().body(profesorService.updateProfesor(profesor, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesor (@PathVariable String id){
        profesorService.deleteProfesor(id);
        return ResponseEntity.ok().body("Se ha borrado el profesor con id: " + id);
    }
}
