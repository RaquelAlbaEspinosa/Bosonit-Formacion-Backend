package com.bosonit.formacion.block7crud.application;

import com.bosonit.formacion.block7crud.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crud.domain.Persona;
import com.bosonit.formacion.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceslmpl implements PersonaServices {
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto addPersona (PersonaInputDto persona){
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto updatePersonaById (PersonaInputDto persona, int id){
        Persona personaProvisional = personaRepository.findById(id).orElseThrow();
        persona.setNombre(persona.getNombre() != null ? persona.getNombre() : personaProvisional.getNombre());
        persona.setEdad(persona.getEdad() != null ? persona.getEdad() : personaProvisional.getEdad());
        persona.setPoblacion(persona.getPoblacion() != null ? persona.getPoblacion() : personaProvisional.getPoblacion());

        return personaRepository.save(new Persona(id, persona.getNombre(), persona.getEdad(), persona.getPoblacion()))
                .personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto deletePersonaById (int id){
        Persona personaProvisional = personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
        return personaProvisional.personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto getPersonaById (int id){
        return personaRepository.findById(id).orElseThrow()
                .personaToPersonaOutputDto();
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByName(String name, int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getNombre().equals(name))
                .map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersona(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }
}
