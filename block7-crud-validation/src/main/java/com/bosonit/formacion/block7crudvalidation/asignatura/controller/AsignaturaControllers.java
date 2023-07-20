package com.bosonit.formacion.block7crudvalidation.asignatura.controller;

import com.bosonit.formacion.block7crudvalidation.asignatura.application.AsignaturaServices;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.student.application.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaControllers {
    @Autowired
    AsignaturaServices asignaturaServices;
    @Autowired
    StudentService studentService;
    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura (@Valid @RequestBody AsignaturaInputDto asignatura){
        URI location = URI.create("/asignatura");
        return ResponseEntity.created(location).body(asignaturaServices.addAsignatura(asignatura));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById (@PathVariable String id){
        try {
            return ResponseEntity.ok().body(asignaturaServices.getAsignaturaById(id));
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @GetMapping
    public Iterable<AsignaturaOutputDto> getAllAsignatura (@RequestParam(defaultValue = "0", required = false)
                                                           int pageNumber,
                                                           @RequestParam(defaultValue = "4", required = false)
                                                           int pageSize) {
        return asignaturaServices.getAllAsignatura(pageNumber, pageSize);
    }
    @GetMapping("/studentid/{idStudent}")
    public Iterable<AsignaturaOutputDto> getAsignaturaByStudentId (@PathVariable String idStudent){
        studentService.getStudentById(idStudent);
        return asignaturaServices.getAsignaturaByStudentId(idStudent);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> updateAsignatura (@Valid @RequestBody AsignaturaInputDto asignatura,
                                                 @PathVariable String id){
        asignaturaServices.getAsignaturaById(id);
        return ResponseEntity.ok().body(asignaturaServices.updateAsignatura(asignatura, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsignatura (@PathVariable String id){
        asignaturaServices.deleteAsignatura(id);
        return ResponseEntity.ok().body("Se ha borrado la asignatura con id: " + id);
    }
}
