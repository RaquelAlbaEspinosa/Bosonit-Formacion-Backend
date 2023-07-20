package com.bosonit.formacion.block7crudvalidation.persona.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEstudianteOutputDto extends PersonaOutputDto {
    private String idStudent;
    private int numHoursWeek;
    private String comments;
    private String branch;
}
