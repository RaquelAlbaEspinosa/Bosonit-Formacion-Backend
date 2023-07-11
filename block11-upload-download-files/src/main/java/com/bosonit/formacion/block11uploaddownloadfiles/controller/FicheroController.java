package com.bosonit.formacion.block11uploaddownloadfiles.controller;

import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroService;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichero")
public class FicheroController {
    @Autowired
    FicheroService ficheroService;
    @PostMapping
    public FicheroOutputDto addFichero (@RequestBody FicheroInputDto ficheroInputDto){
        return ficheroService.addFichero(ficheroInputDto);
    }
    @GetMapping
    public List<FicheroOutputDto> getAllFichero (@RequestParam (defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam (defaultValue = "4", required = false) int pageSize){
        return ficheroService.getAllFichero(pageNumber, pageSize);
    }
    @GetMapping("/{id}")
    public FicheroOutputDto getFicheroById (@PathVariable int id){
        return ficheroService.getFicheroById(id);
    }
    @GetMapping("/{name}")
    public FicheroOutputDto getFicheroByName (@PathVariable String name){
        return ficheroService.getFicheroByName(name);
    }
    @PutMapping("/{id}")
    public FicheroOutputDto uploadFichero (@PathVariable int id,
                                           @RequestBody FicheroInputDto ficheroInputDto){
        return ficheroService.uploadFichero(id, ficheroInputDto);
    }
    @DeleteMapping("/{id}")
    public String deleteFichero (@PathVariable int id){
        ficheroService.deleteFichero(id);
        return "Se ha borrado el fichero con id: " + id;
    }
}
