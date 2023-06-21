package com.bosonit.formacion.block7crudvalidation.application;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;

public interface PersonaServices {
    PersonaOutputDto getPersonaById (int id);
    Iterable<PersonaOutputDto> getPersonaByUsuario (String usuario);
    Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);
}
