package com.bosonit.formacion.block7crudvalidation.persona.application;


import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto getPersonaById (int id){
        return personaRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .personaToPersonaOutputDto();
    }
    @Override
    public PersonaEstudianteOutputDto getPersonaByIdEstudiante (int id) {
        return personaRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .personaToPersonaEstudianteOutputDto();
    }
    @Override
    public PersonaProfesorOutputDto getPersonaByIdProfesor (int id) {
        return personaRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .personaToPersonaProfesorOutputDto();
    }
    @Override
    public Iterable<PersonaOutputDto> getPersonaByUsuario (String usuario){
         return personaRepository.findAll()
                .stream() .filter(persona -> persona.getUsuario().equals(usuario))
                .map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public Iterable getPersonaByUsuarioFull (String usuario){
         return personaRepository.findAll()
                .stream() .filter(persona -> persona.getUsuario().equals(usuario))
                .map(persona -> {
                    if(persona.getProfesor() != null){
                        return persona.personaToPersonaProfesorOutputDto();
                    } else if (persona.getStudent() != null) {
                        return persona.personaToPersonaEstudianteOutputDto();
                    } else {
                        return persona.personaToPersonaOutputDto();
                    }
                }).toList();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public Iterable getAllPersonaFull (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().map(persona -> {
                    if (persona.getProfesor() != null) {
                        return persona.personaToPersonaProfesorOutputDto();
                    } else if (persona.getStudent() != null) {
                        return persona.personaToPersonaEstudianteOutputDto();
                    } else {
                        return persona.personaToPersonaOutputDto();
                    }
                }).toList();
    }
    @Override
    public PersonaOutputDto updatePersona (PersonaInputDto persona, int id){
        Persona personaProvisional = personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        persona.setId(id);
        persona.setSurname(persona.getSurname() != null ?
                persona.getSurname() : personaProvisional.getSurname());
        persona.setImageUrl(persona.getImageUrl() != null ?
                persona.getImageUrl() : personaProvisional.getImageUrl());
        persona.setTerminationDate(persona.getTerminationDate() != null ?
                persona.getTerminationDate() : personaProvisional.getTerminationDate());
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    @Override
    public void deletePersona (int id){
        personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personaRepository.deleteById(id);
    }
    public String getTypeOfPersona (int id) {
        String typeOfPersona;
        Persona persona = personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
