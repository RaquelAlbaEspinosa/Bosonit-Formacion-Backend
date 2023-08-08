package com.bosonit.formacion.block7crudvalidation.student.controller.dto;

import lombok.*;

@Getter
@Setter
public class StudentOutputDto {
    private String idStudent;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private String idProfesor;
}
