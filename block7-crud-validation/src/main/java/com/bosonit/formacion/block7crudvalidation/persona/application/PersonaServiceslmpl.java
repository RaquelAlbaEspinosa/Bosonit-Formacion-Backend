package com.bosonit.formacion.block7crudvalidation.persona.application;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.mapper.PersonaMapper;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceslmpl implements PersonaServices{
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Override
    public PersonaOutputDto addPersona (PersonaInputDto persona){
        PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
        Persona persona1 = new Persona(persona);
        personaRepository.save(persona1);
        return mapper.personaToPersonaOutputDto(persona1);
    }
    @Override
    public PersonaOutputDto getPersonaById (int id){
        PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personaToPersonaOutputDto(persona);
    }
    @Override
    public PersonaEstudianteOutputDto getPersonaByIdEstudiante (int id) {
        PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personaToPersonaEstudianteOutputDto(persona);
    }
    @Override
    public PersonaProfesorOutputDto getPersonaByIdProfesor (int id) {
        PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personaToPersonaProfesorOutputDto(persona);
    }
    @Override
    public Iterable<PersonaOutputDto> getPersonaByUsuario (String usuario){
         return personaRepository.findAll()
                .stream()
                .map(persona -> {
                    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
                    return mapper.personaToPersonaOutputDto(persona);
                }).toList();
    }
    @Override
    public Iterable getPersonaByUsuarioFull (String usuario){
         return personaRepository.findAll()
                .stream() .filter(persona -> persona.getUsuario().equals(usuario))
                .map(persona -> {
                    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
                    if(persona.getProfesor() != null){
                        return mapper.personaToPersonaProfesorOutputDto(persona);
                    } else if (persona.getStudent() != null) {
                        return mapper.personaToPersonaEstudianteOutputDto(persona);
                    } else {
                        return mapper.personaToPersonaOutputDto(persona);
                    }
                }).toList();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().map(persona -> {
                    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
                    return mapper.personaToPersonaOutputDto(persona);
                }).toList();
    }
    @Override
    public Iterable getAllPersonaFull (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().map(persona -> {
                    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
                    if(persona.getProfesor() != null){
                        return mapper.personaToPersonaProfesorOutputDto(persona);
                    } else if (persona.getStudent() != null) {
                        return mapper.personaToPersonaEstudianteOutputDto(persona);
                    } else {
                        return mapper.personaToPersonaOutputDto(persona);
                    }
                }).toList();
    }
    @Override
    public PersonaOutputDto updatePersona (PersonaInputDto persona, int id){
        PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
        Persona personaProvisional = personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        persona.setId(id);
        persona.setSurname(persona.getSurname() != null ?
                persona.getSurname() : personaProvisional.getSurname());
        persona.setImageUrl(persona.getImageUrl() != null ?
                persona.getImageUrl() : personaProvisional.getImageUrl());
        persona.setTerminationDate(persona.getTerminationDate() != null ?
                persona.getTerminationDate() : personaProvisional.getTerminationDate());
        Persona persona1 = new Persona(persona);
        personaRepository.save(persona1);
        return mapper.personaToPersonaOutputDto(persona1);
    }
    @Override
    public void deletePersona (int id){
        Persona persona = personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(persona.getStudent() != null){
            Student student = persona.getStudent();
            if (student.getProfesor() != null){
                Profesor profesorProvisional = profesorRepository
                        .findById(student.getProfesor().getIdProfesor())
                        .orElseThrow(EntityNotFoundException::new);
                profesorProvisional.getStudents().remove(student);
            }
        }
        if (persona.getProfesor() != null){
            Profesor profesor = persona.getProfesor();
            if(profesor.getStudents() != null){
                List <Student> students = profesor.getStudents();
                students.forEach(student ->
                    studentRepository.findById(student.getIdStudent())
                            .orElseThrow(EntityNotFoundException::new)
                            .setProfesor(null)
                );
            }
        }
        personaRepository.deleteById(id);
    }
    public String getTypeOfPersona (int id) {
        String typeOfPersona;
        Persona persona = personaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (persona.getStudent() != null){
            typeOfPersona = "Estudiante";
        } else if (persona.getProfesor() != null){
            typeOfPersona = "Profesor";
        } else {
            typeOfPersona = "none";
        }
        return typeOfPersona;
    }
}
