package com.bosonit.formacion.block7crudvalidation.student.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    String idStudent;
    @NotNull(message = "El número de horas a la semana no debe ser nulo")
    Integer numHoursWeek;
    String comments;
    @NotBlank(message = "La rama no debe ser nula")
    String branch;
    int idPersona;
    String idProfesor;
}
