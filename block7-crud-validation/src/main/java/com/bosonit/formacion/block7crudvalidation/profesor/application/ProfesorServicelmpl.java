package com.bosonit.formacion.block7crudvalidation.profesor.application;

import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper.ProfesorMapper;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServicelmpl implements ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public ProfesorOutputDto addProfesor(@Valid ProfesorInputDto profesor) {
        ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
        Persona persona = personaRepository.findById(profesor.getIdPersona())
                .orElseThrow(EntityNotFoundException::new);
        Profesor profesor1 = mapper.profesorInputDtoProfesor(profesor);
        persona.setProfesor(profesor1);
        profesor1.setPersona(persona);
        profesorRepository.save(profesor1);
        return mapper.profesorToProfesorOutputDto(profesor1);
    }

    @Override
    public ProfesorOutputDto getProfesorById(String id) {
        ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.profesorToProfesorOutputDto(profesor);
    }

    @Override
    public ProfesorFullOutputDto getProfesorByIdFull(String id) {
        ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
        Profesor profesor =  profesorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.profesorToProfesorFullOutputDto(profesor);
    }

    @Override
    public Iterable<ProfesorOutputDto> getProfesorByUsuario(String usuario) {
        List<Integer> idList = personaRepository.findAll().stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(Persona::getIdPersona).toList();
        return profesorRepository.findAll().stream().filter(profesor -> {
            boolean check = false;
            for(Integer id : idList){
                if (profesor.getPersona().getIdPersona() == id) {
                    check = true;
                }
            }
            return check;
        }).map(profesor -> {
            ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
            return mapper.profesorToProfesorOutputDto(profesor);
        }).toList();
    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesor(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(pageRequest).getContent().stream()
                .map(profesor -> {
                    ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
                    return mapper.profesorToProfesorOutputDto(profesor);
                }).toList();
    }

    @Override
    public ProfesorOutputDto updateProfesor(@Valid ProfesorInputDto profesor, String id) {
        ProfesorMapper mapper = Mappers.getMapper(ProfesorMapper.class);
        Profesor profesorProvisional = profesorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        profesor.setComments(profesor.getComments() != null ?
                profesor.getComments() : profesorProvisional.getComments());
        Profesor profesor1 = mapper.profesorInputDtoProfesor(profesor);
        profesor1.setIdProfesor(id);
        if(profesor.getIdPersona() != 0){
            int idPersonaOriginal = profesorProvisional.getPersona().getIdPersona();
            personaRepository.findById(idPersonaOriginal)
                    .orElseThrow(EntityNotFoundException::new)
                    .setProfesor(null);
            Persona personaProvisional = personaRepository.findById(profesor.getIdPersona())
                    .orElseThrow(EntityNotFoundException::new);
            profesor1.setPersona(personaProvisional);
            personaProvisional.setProfesor(profesor1);
            personaProvisional.setIdPersona(personaProvisional.getIdPersona());
            personaRepository.save(personaProvisional);
        } else {
            profesor1.setPersona(profesorProvisional.getPersona());
        }
        profesorRepository.save(profesor1);
        return mapper.profesorToProfesorOutputDto(profesor1);
    }

    @Override
    public void deleteProfesor(String id) {
        Profesor profesorProvisional = profesorRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if(profesorProvisional.getPersona() != null){
            Persona personaProvisional = personaRepository
                    .findById(profesorProvisional.getPersona().getIdPersona())
                    .orElseThrow(EntityNotFoundException::new);
            personaProvisional.setProfesor(null);
        }
        if(profesorProvisional.getStudents() != null){
            profesorProvisional.getStudents().forEach(student ->
                studentRepository
                        .findById(student.getIdStudent()).orElseThrow(EntityNotFoundException::new)
                        .setProfesor(null)
            );
        }
        profesorRepository.delete(profesorProvisional);
    }
}
