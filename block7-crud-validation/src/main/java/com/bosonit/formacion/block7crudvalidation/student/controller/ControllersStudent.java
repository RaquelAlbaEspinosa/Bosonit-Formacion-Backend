package com.bosonit.formacion.block7crudvalidation.student.controller;

import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.student.application.StudentService;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class ControllersStudent {
    @Autowired
    StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent (@Valid @RequestBody StudentInputDto student){
        URI location = URI.create("/student");
        try {
            return ResponseEntity.created(location).body(studentService.addStudent(student));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getStudentById (@PathVariable String id,
                                          @RequestParam(defaultValue = "simple", required = false)
                                          String outputType){
        if(outputType.equals("simple")){
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } else if (outputType.equals("full")){
            return ResponseEntity.ok().body(studentService.getStudentByIdFull(id));
        } else {
            return ResponseEntity.unprocessableEntity().body("outputType debe ser 'simple' o 'full'");
        }
    }
    @GetMapping("/usuario/{usuario}")
    public Iterable<StudentOutputDto> getStudentByUsuario (@PathVariable String usuario){
        return studentService.getStudentByUsuario(usuario);
    }
    @GetMapping
    public Iterable<StudentOutputDto> getAllStudent (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize){
        return studentService.getAllStudent(pageNumber, pageSize);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent (@RequestBody StudentInputDto student,
                                                           @PathVariable String id){

        studentService.getStudentById(id);
        return ResponseEntity.ok().body(studentService.updateStudent(student, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable String id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Se ha borrado el estudiante con id: " + id);
    }
    @PutMapping("/{idStudent}/{idAsignatura}")
    public ResponseEntity<String> addAsignaturaToStudent (@PathVariable String idStudent,
                                                          @PathVariable String idAsignatura){
        studentService.getStudentById(idStudent);
        studentService.addAsignaturaToStudent(idStudent, idAsignatura);
        return ResponseEntity.ok()
                .body("Se ha añadido la asignatura con id " + idAsignatura + " al estudiante con id " + idStudent);
    }
}
