package com.bosonit.formacion.block7crudvalidation.asignatura.application;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;

public interface AsignaturaServices {
    AsignaturaOutputDto addAsignatura (AsignaturaInputDto asignatura);
    AsignaturaOutputDto getAsignaturaById (String id);
    Iterable<AsignaturaOutputDto> getAllAsignatura (int pageNumber, int pageSize);
    Iterable<AsignaturaOutputDto> getAsignaturaByStudentId (String idStudent);
    AsignaturaOutputDto updateAsignatura (AsignaturaInputDto asignatura, String id);
    void deleteAsignatura (String id);
}
