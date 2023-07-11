package com.bosonit.formacion.block7crudvalidation.student.application;

import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;

public interface StudentService {
    StudentOutputDto addStudent (StudentInputDto student);
    StudentOutputDto getStudentById (String id);
    StudentFullOutputDto getStudentByIdFull (String id);
    Iterable<StudentOutputDto> getStudentByUsuario (String usuario);
    Iterable<StudentOutputDto> getAllStudent (int pageNumber, int pageSize);
    StudentOutputDto updateStudent (StudentInputDto student, String id);
    void deleteStudent (String id);
    void addAsignaturaToStudent (String idStudent, String idAsignatura);
}
