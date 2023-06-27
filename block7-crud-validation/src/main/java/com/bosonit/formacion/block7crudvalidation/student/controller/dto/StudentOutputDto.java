package com.bosonit.formacion.block7crudvalidation.student.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    String idStudent;
    int numHoursWeek;
    String comments;
    String branch;
    String idProfesor;
}
