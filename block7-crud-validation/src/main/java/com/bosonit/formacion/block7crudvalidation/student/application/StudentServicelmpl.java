package com.bosonit.formacion.block7crudvalidation.student.application;

import com.bosonit.formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServicelmpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Override
    public StudentOutputDto addStudent(@Valid StudentInputDto student) {
        Persona persona = personaRepository.findById(student.getIdPersona())
                .orElseThrow(EntityNotFoundException::new);
        Profesor profesor = null;
        Student student1 = new Student(student);
        if(student.getIdProfesor() != null){
            profesor = profesorRepository.findById(student.getIdProfesor())
                    .orElseThrow(EntityNotFoundException::new);
            profesor.getStudents().add(student1);
        }
        persona.setStudent(student1);
        student1.setPersona(persona);
        student1.setProfesor(profesor);
        return studentRepository.save(student1).studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .studentToStudentOutputDto();
    }
    @Override
    public StudentFullOutputDto getStudentByIdFull(String id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .studentToStudentFullOutputDto();
    }

    @Override
    public Iterable<StudentOutputDto> getStudentByUsuario(String usuario) {
        List<Integer> IdList = personaRepository.findAll().stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(Persona::getIdPersona).toList();
        return studentRepository.findAll().stream().filter(student -> {
            boolean check = false;
            for(Integer id : IdList){
            if(student.getPersona().getIdPersona() == id){
                check = true;
            }
        } return check;}).map(Student::studentToStudentOutputDto).toList();
    }
    @Override
    public Iterable<StudentOutputDto> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent().stream()
                .map(Student::studentToStudentOutputDto).toList();
    }
    @Override
    public StudentOutputDto updateStudent (@Valid StudentInputDto student, String id){
        Student studentProvisional = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        student.setComments(student.getComments() != null ?
                student.getComments() : studentProvisional.getComments());
        Student student1 = new Student(student);
        student1.setIdStudent(id);
        if (student.getIdPersona() != 0){
            int idPersonaOriginal = studentProvisional.getPersona().getIdPersona();
            personaRepository.findById(idPersonaOriginal).orElseThrow(EntityNotFoundException::new).setStudent(null);
            Persona personaProvisional = personaRepository.findById(student.getIdPersona())
                    .orElseThrow(EntityNotFoundException::new);
            student1.setPersona(personaProvisional);
            personaProvisional.setStudent(student1);
        } else {
            student1.setPersona(studentProvisional.getPersona());
        }
        if (student.getIdProfesor() != null){
            if(studentProvisional.getProfesor() != null){
                String idProfesorOriginal = studentProvisional.getProfesor().getIdProfesor();
                profesorRepository.findById(idProfesorOriginal).orElseThrow(EntityNotFoundException::new)
                        .getStudents().remove(studentProvisional);
            }
            Profesor profesorProvisional = profesorRepository.findById(student.getIdProfesor())
                    .orElseThrow(EntityNotFoundException::new);
            student1.setProfesor(profesorProvisional);
            profesorProvisional.getStudents().add(student1);
        } else {
            student1.setProfesor(studentProvisional.getProfesor());
        }
        return studentRepository.save(student1).studentToStudentOutputDto();
    }
    @Override
    public void deleteStudent (String id){
        Student studentProvisional = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (studentProvisional.getPersona() != null) {
            Persona personaProvisional = personaRepository.findById(studentProvisional.getPersona()
                    .getIdPersona()).orElseThrow(EntityNotFoundException::new);
            personaProvisional.setStudent(null);
        }
        if(studentProvisional.getProfesor() != null){
            Profesor profesorProvisional = profesorRepository.findById(studentProvisional.getProfesor().getIdProfesor())
                    .orElseThrow(EntityNotFoundException::new);
            profesorProvisional.getStudents().remove(studentProvisional);
        }
        studentRepository.delete(studentProvisional);
    }
}
