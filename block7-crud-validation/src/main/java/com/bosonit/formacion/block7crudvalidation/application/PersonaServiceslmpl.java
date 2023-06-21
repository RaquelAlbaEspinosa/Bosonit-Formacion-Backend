package com.bosonit.formacion.block7crudvalidation.application;


import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceslmpl implements PersonaServices{
    @Autowired
    PersonaRepository personaRepository;
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
    public Iterable<PersonaOutputDto> getPersonaByUsuario (String usuario){
         return personaRepository.findAll()
                .stream() .filter(persona -> persona.getUsuario().equals(usuario))
                .map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().map(Persona::personaToPersonaOutputDto).toList();
    }
}
