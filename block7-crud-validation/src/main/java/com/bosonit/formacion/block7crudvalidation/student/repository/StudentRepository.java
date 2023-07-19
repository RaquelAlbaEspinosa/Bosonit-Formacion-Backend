package com.bosonit.formacion.block7crudvalidation.student.repository;

import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<StudentOutputDto> getCustomQuery(HashMap<String, Object> conditions, String orderBy, int pageNumber, int pageSize);
}
