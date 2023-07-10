package com.bosonit.formacion.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FicheroOutputDto {
    int id;
    String fileName;
    Date uploadDate;
    String category;
}
