package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.domain.Fichero;
import com.bosonit.formacion.block11uploaddownloadfiles.repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheroServiceImpl implements FicheroService{
    @Autowired
    FicheroRepository ficheroRepository;
    @Override
    public FicheroOutputDto addFichero(FicheroInputDto fichero) {
        return ficheroRepository.save(new Fichero(fichero)).ficherotoFicheroOutputDto();
    }

    @Override
    public List<FicheroOutputDto> getAllFichero(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return ficheroRepository.findAll(pageRequest).getContent()
                .stream()
                .map(fichero -> fichero.ficherotoFicheroOutputDto())
                .toList();
    }

    @Override
    public FicheroOutputDto getFicheroById(int id) {
        return ficheroRepository.findById(id).orElseThrow().ficherotoFicheroOutputDto();
    }

    @Override
    public FicheroOutputDto getFicheroByName(String name) {
        return ficheroRepository.findByFileName(name).orElseThrow().ficherotoFicheroOutputDto();
    }

    @Override
    public FicheroOutputDto uploadFichero(int id, FicheroInputDto ficheroInputDto) {
        Fichero fichero = new Fichero(ficheroInputDto);
        fichero.setId(id);
        return ficheroRepository.save(fichero).ficherotoFicheroOutputDto();
    }

    @Override
    public void deleteFichero(int id) {
        ficheroRepository.findById(id).orElseThrow();
        ficheroRepository.deleteById(id);
    }
}
