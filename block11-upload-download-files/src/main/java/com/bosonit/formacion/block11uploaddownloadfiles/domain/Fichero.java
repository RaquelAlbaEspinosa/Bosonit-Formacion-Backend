package com.bosonit.formacion.block11uploaddownloadfiles.domain;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    int id;
    String fileName;
    Date uploadDate;
    String category;
    public Fichero (FicheroInputDto ficheroInputDto){
        this.id = ficheroInputDto.getId();
        this.fileName = ficheroInputDto.getFileName();
        this.uploadDate = ficheroInputDto.getUploadDate();
        this.category = ficheroInputDto.getCategory();
    }
    public FicheroOutputDto ficherotoFicheroOutputDto () {
        return new FicheroOutputDto(
                this.id,
                this.fileName,
                this.uploadDate,
                this.category
        );
    }
}
