package com.bosonit.formacion.block13mongodb.application;

import com.bosonit.formacion.block13mongodb.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13mongodb.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block13mongodb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceslmpl implements PersonaServices{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonaOutputDto addPersona (PersonaInputDto persona){
        Persona persona1 = new Persona(persona);
        mongoTemplate.save(persona1);
        return persona1.personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto getPersonaById (String id){
        Persona persona = mongoTemplate.findById(id, Persona.class);
        return persona.personaToPersonaOutputDto();
    }
    @Override
    public Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Query query = new Query();
        query.with(pageRequest);
        return mongoTemplate.find(query, Persona.class)
                .stream().map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public PersonaOutputDto updatePersona (PersonaInputDto persona, String id){
        Persona personaProvisional = mongoTemplate.findById(id, Persona.class);
        persona.setIdPersona(id);
        persona.setSurname(persona.getSurname() != null ?
                persona.getSurname() : personaProvisional.getSurname());
        persona.setImageUrl(persona.getImageUrl() != null ?
                persona.getImageUrl() : personaProvisional.getImageUrl());
        persona.setTerminationDate(persona.getTerminationDate() != null ?
                persona.getTerminationDate() : personaProvisional.getTerminationDate());
        Persona persona1 = new Persona(persona);
        mongoTemplate.save(persona1);
        return persona1.personaToPersonaOutputDto();
    }
    @Override
    public void deletePersona (String id){
        Query query = new Query(Criteria.where("idPersona").is(id));
        mongoTemplate.remove(query, Persona.class);
    }
}
