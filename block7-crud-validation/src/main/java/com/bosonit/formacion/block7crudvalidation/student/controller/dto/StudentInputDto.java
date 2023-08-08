package com.bosonit.formacion.block7crudvalidation.student.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class StudentInputDto {
    private String idStudent;
    @NotNull(message = "El número de horas a la semana no debe ser nulo")
    private Integer numHoursWeek;
    private String comments;
    @NotBlank(message = "La rama no debe ser nula")
    private String branch;
    private int idPersona;
    private String idProfesor;
}
