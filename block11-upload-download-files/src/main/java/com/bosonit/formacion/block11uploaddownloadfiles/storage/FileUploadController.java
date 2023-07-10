package com.bosonit.formacion.block11uploaddownloadfiles.storage;

import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroService;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageFileNotFoundException;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {
    private final StorageService storageService;
    @Autowired
    public FicheroService ficheroService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<?> uploadFileByType (@RequestBody MultipartFile file,
                                               @PathVariable String tipo){
        URI location = URI.create("/fichero");
        String tipoFile = file.getOriginalFilename().split("\\.")[1];
            if (tipo.equals(tipoFile)) {
                storageService.store(file);
                FicheroInputDto ficheroInputDto = new FicheroInputDto();
                ficheroInputDto.setFileName(file.getOriginalFilename().split("\\.")[0]);
                ficheroInputDto.setUploadDate(new Date());
                ficheroInputDto.setCategory(tipo);
                ficheroService.addFichero(ficheroInputDto);
                return ResponseEntity.created(location).body("You successfully uploaded " + file.getOriginalFilename() + "!");
            }
            else {
                return ResponseEntity.badRequest().body("You can't upload that type of file through this endpoint," +
                        "it must be a " + tipoFile + " file");
            }
    }
    @GetMapping("/setpath")
    public ResponseEntity<?> setRepoPath (@RequestParam Path path){
        storageService.changeLocation(path);
        return ResponseEntity.ok().body("Cambiado la localización de los archivos a: " + path);
    }
    @GetMapping("/file")
    @ResponseBody
    public ResponseEntity<?> loadFileByName (@RequestParam(required = false) String filename,
                                             @RequestParam(required = false) Integer id){
        try {
            Resource file = null;
            if(id != null){
                String filenameById = ficheroService.getFicheroById(id).getFileName()
                        + "."
                        + ficheroService.getFicheroById(id).getCategory();
                file = storageService.loadAsResource(filenameById);
            }
            if(filename != null) {
                file = storageService.loadAsResource(filename);
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"")
                    .body("You are downloading the file: " + filename);
        } catch (StorageFileNotFoundException e){
            throw new StorageFileNotFoundException("Así no, eso no existe, malamente tratra");
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
