package com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper;

import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfesorMapper {
    ProfesorMapper INSTANCE = Mappers.getMapper(ProfesorMapper.class);
    ProfesorOutputDto profesorToProfesorOutputDto (Profesor profesor);
    @Mapping(target = "idPersona", source = "profesor.persona.idPersona")
    @Mapping(target = "usuario", source = "profesor.persona.usuario")
    @Mapping(target = "password", source = "profesor.persona.password")
    @Mapping(target = "name", source = "profesor.persona.name")
    @Mapping(target = "surname", source = "profesor.persona.surname")
    @Mapping(target = "companyEmail", source = "profesor.persona.companyEmail")
    @Mapping(target = "personalEmail", source = "profesor.persona.personalEmail")
    @Mapping(target = "city", source = "profesor.persona.city")
    @Mapping(target = "active", source = "profesor.persona.active")
    @Mapping(target = "createdDate", source = "profesor.persona.createdDate")
    @Mapping(target = "imageUrl", source = "profesor.persona.imageUrl")
    @Mapping(target = "terminationDate", source = "profesor.persona.terminationDate")
    ProfesorFullOutputDto profesorToProfesorFullOutputDto (Profesor profesor);
    Profesor profesorInputDtoProfesor (ProfesorInputDto profesorInputDto);
}
