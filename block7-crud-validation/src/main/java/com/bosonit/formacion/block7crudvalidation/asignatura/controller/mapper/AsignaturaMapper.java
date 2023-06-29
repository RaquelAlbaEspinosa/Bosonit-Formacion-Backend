package com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AsignaturaMapper {
    AsignaturaMapper INSTANCE = Mappers.getMapper(AsignaturaMapper.class);
    @Mapping(target = "asignatura", source = "asignatura.asignatura")
    AsignaturaOutputDto asignaturaToAsignaturaOutputDto (Asignatura asignatura);
    Asignatura asignaturaInputDtoToAsignatura (AsignaturaInputDto asignaturaInputDto);
}
