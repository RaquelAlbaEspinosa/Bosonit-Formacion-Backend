package com.bosonit.formacion.block7crudvalidation.profesor.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorInputDto {
    private String idProfesor;
    private String comments;
    @NotBlank(message = "La rama no puede ser nula")
    private String branch;
    private int idPersona;
}
