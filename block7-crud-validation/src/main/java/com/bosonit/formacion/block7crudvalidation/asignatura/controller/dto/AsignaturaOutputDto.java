package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AsignaturaOutputDto {
    private String idStudy;
    private String nombreAsignatura;
    private String comments;
    private Date initialDate;
    private Date finishDate;
}
