package com.bosonit.formacion.block13mongodb.application;

import com.bosonit.formacion.block13mongodb.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13mongodb.controller.dto.PersonaOutputDto;

public interface PersonaServices {
    PersonaOutputDto addPersona (PersonaInputDto persona);
    PersonaOutputDto getPersonaById (String id);
    Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);
    PersonaOutputDto updatePersona (PersonaInputDto persona, String id);
    void deletePersona (String id);
}
