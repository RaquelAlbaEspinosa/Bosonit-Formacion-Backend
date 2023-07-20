package com.bosonit.formacion.block7crudvalidation.persona.controller.mapper;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonaMapper {
    PersonaOutputDto personaToPersonaOutputDto (Persona persona);
    @Mapping(target = "idStudent", source = "persona.student.idStudent")
    @Mapping(target = "numHoursWeek", source = "persona.student.numHoursWeek")
    @Mapping(target = "comments", source = "persona.student.comments")
    @Mapping(target = "branch", source = "persona.student.branch")
    PersonaEstudianteOutputDto personaToPersonaEstudianteOutputDto (Persona persona);
    @Mapping(target = "idProfesor", source = "persona.profesor.idProfesor")
    @Mapping(target = "comments", source = "persona.profesor.comments")
    @Mapping(target = "branch", source = "persona.profesor.branch")
    PersonaProfesorOutputDto personaToPersonaProfesorOutputDto (Persona persona);
}
