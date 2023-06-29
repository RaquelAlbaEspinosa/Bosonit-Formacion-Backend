package com.bosonit.formacion.block7crudvalidation.profesor.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDto {
    String idProfesor;
    String comments;
    @NotBlank(message = "La rama no puede ser nula")
    String branch;
    int idPersona;
}
