package com.bosonit.formacion.block7crudvalidation.persona.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaProfesorOutputDto extends PersonaOutputDto {
    private String idProfesor;
    private String comments;
    private String branch;
}
