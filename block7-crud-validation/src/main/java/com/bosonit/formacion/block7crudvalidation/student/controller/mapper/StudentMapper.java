package com.bosonit.formacion.block7crudvalidation.student.controller.mapper;

import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    @Mapping(target = "idProfesor", source = "student.profesor.idProfesor")
    StudentOutputDto studentToStudentOutputDto (Student student);

    @Mapping(target = "idProfesor", source = "student.profesor.idProfesor")
    @Mapping(target = "idPersona", source = "student.persona.idPersona")
    @Mapping(target = "usuario", source = "student.persona.usuario")
    @Mapping(target = "password", source = "student.persona.password")
    @Mapping(target = "name", source = "student.persona.name")
    @Mapping(target = "surname", source = "student.persona.surname")
    @Mapping(target = "companyEmail", source = "student.persona.companyEmail")
    @Mapping(target = "personalEmail", source = "student.persona.personalEmail")
    @Mapping(target = "city", source = "student.persona.city")
    @Mapping(target = "active", source = "student.persona.active")
    @Mapping(target = "createdDate", source = "student.persona.createdDate")
    @Mapping(target = "imageUrl", source = "student.persona.imageUrl")
    @Mapping(target = "terminationDate", source = "student.persona.terminationDate")
    StudentFullOutputDto studentToStudentFullOutputDto (Student student);
    Student studentInputDtoToStudent (StudentInputDto studentInputDto);
}
