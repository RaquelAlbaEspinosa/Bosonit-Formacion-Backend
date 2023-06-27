package com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaInputDto {
    String idStudy;
    String IdStudent;
    @NotNull(message = "La asignatura no debe ser nula")
    String asignatura;
    String comments;
    @NotNull(message = "La fecha de inicio no debe ser nula")
    Date initialDate;
    Date finishDate;
}
