package com.bosonit.formacion.block7crud.application;

import com.bosonit.formacion.block7crud.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crud.controller.dto.PersonaOutputDto;

public interface PersonaServices {
    PersonaOutputDto addPersona (PersonaInputDto persona);
    PersonaOutputDto updatePersonaById (PersonaInputDto persona, int id);
    PersonaOutputDto deletePersonaById (int id);
    PersonaOutputDto getPersonaById (int id);
    Iterable<PersonaOutputDto> getPersonaByName (String name,  int pageNumber, int pageSize);
    Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);

}
