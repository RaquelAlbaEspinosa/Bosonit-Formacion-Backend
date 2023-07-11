package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;

import java.util.List;

public interface FicheroService {
    FicheroOutputDto addFichero (FicheroInputDto fichero);
    List<FicheroOutputDto> getAllFichero (int pageNumber, int pageSize);
    FicheroOutputDto getFicheroById (int id);
    FicheroOutputDto getFicheroByName (String name);
    FicheroOutputDto uploadFichero (int id, FicheroInputDto ficheroInputDto);
    void deleteFichero (int id);
}
