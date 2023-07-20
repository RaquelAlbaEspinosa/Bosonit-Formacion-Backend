package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaInputDto {
    String idStudy;
    String idStudent;
    @NotNull(message = "La asignatura no debe ser nula")
    String nombreAsignatura;
    String comments;
    @NotNull(message = "La fecha inicial no puede ser nula")
    Date initialDate;
    Date finishDate;
}
