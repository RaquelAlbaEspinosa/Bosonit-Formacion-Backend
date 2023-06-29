package com.bosonit.formacion.block7crudvalidation.student.repository;

import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
