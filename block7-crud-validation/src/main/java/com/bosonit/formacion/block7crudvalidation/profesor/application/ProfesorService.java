package com.bosonit.formacion.block7crudvalidation.profesor.application;

import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;

public interface ProfesorService {
    ProfesorOutputDto addProfesor (ProfesorInputDto profesor);
    ProfesorOutputDto getProfesorById (String id);
    ProfesorFullOutputDto getProfesorByIdFull (String id);
    Iterable<ProfesorOutputDto> getProfesorByUsuario (String usuario);
    Iterable<ProfesorOutputDto> getAllProfesor (int pageNumber, int pageSize);
    ProfesorOutputDto updateProfesor (ProfesorInputDto profesor, String id);
    void deleteProfesor (String id);
}
