package com.bosonit.formacion.block7crudvalidation.persona.application;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;

public interface PersonaServices {
    PersonaOutputDto addPersona (PersonaInputDto persona);
    PersonaOutputDto getPersonaById (int id);
    PersonaEstudianteOutputDto getPersonaByIdEstudiante (int id);
    PersonaProfesorOutputDto getPersonaByIdProfesor (int id);
    Iterable<PersonaOutputDto> getPersonaByUsuario (String usuario);
    Iterable getPersonaByUsuarioFull (String usuario);
    Iterable<PersonaOutputDto> getAllPersona (int pageNumber, int pageSize);
    Iterable getAllPersonaFull (int pageNumber, int pageSize);
    PersonaOutputDto updatePersona (PersonaInputDto persona, int id);
    void deletePersona (int id);
    String getTypeOfPersona (int id);
}
