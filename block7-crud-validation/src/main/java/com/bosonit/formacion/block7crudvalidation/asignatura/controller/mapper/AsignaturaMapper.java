package com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import org.mapstruct.Mapper;

@Mapper
public interface AsignaturaMapper {
    AsignaturaOutputDto asignaturaToAsignaturaOutputDto (Asignatura asignatura);
    Asignatura asignaturaInputDtoToAsignatura (AsignaturaInputDto asignaturaInputDto);
}
