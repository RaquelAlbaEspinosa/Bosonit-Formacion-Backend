package com.bosonit.formacion.block7crudvalidation.profesor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDto {
    String idProfesor;
    String comments;
    String branch;
}
