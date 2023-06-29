package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDto {
    String idStudy;
    String asignatura;
    String comments;
    Date initialDate;
    Date finishDate;
}
