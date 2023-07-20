package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaInputDto {
    private String idStudy;
    private String idStudent;
    @NotNull(message = "La asignatura no debe ser nula")
    private String nombreAsignatura;
    private String comments;
    @NotNull(message = "La fecha inicial no puede ser nula")
    private Date initialDate;
    private Date finishDate;
}
