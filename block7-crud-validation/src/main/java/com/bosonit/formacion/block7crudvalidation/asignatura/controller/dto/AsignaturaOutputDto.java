package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AsignaturaOutputDto {
    String idStudy;
    String nombreAsignatura;
    String comments;
    Date initialDate;
    Date finishDate;
}
