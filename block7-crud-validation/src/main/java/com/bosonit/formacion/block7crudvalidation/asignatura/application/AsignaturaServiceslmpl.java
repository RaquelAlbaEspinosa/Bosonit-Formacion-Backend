package com.bosonit.formacion.block7crudvalidation.asignatura.application;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper.AsignaturaMapper;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceslmpl implements AsignaturaServices {
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(@Valid AsignaturaInputDto asignatura) {
        AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
        Asignatura asignatura1 = mapper.asignaturaInputDtoToAsignatura(asignatura);
        asignaturaRepository.save(asignatura1);
        return mapper.asignaturaToAsignaturaOutputDto(asignatura1);
    }

    @Override
    public AsignaturaOutputDto getAsignaturaById(String id) {
        AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.asignaturaToAsignaturaOutputDto(asignatura);
    }

    @Override
    public Iterable<AsignaturaOutputDto> getAllAsignatura(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return asignaturaRepository.findAll(pageRequest).getContent().stream()
                .map(asignatura -> {
                    AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
                    return mapper.asignaturaToAsignaturaOutputDto(asignatura);
                }).toList();
    }
    @Override
    public Iterable<AsignaturaOutputDto> getAsignaturaByStudentId (String idStudent){
        Student student = studentRepository.findById(idStudent).orElseThrow();
        return asignaturaRepository.findAll().stream()
                .filter(asignatura -> asignatura.getStudent()
                        .contains(student))
                .map(asignatura -> {
                    AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
                    return mapper.asignaturaToAsignaturaOutputDto(asignatura);
                }).toList();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(@Valid AsignaturaInputDto asignatura, String id) {
        AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
        Asignatura asignaturaProvisional = asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Asignatura asignatura1 = mapper.asignaturaInputDtoToAsignatura(asignatura);
        asignatura1.setIdStudy(asignaturaProvisional.getIdStudy());
        if (asignatura.getIdStudent() != null){
            List<String> idStudentOriginal = asignaturaProvisional.getStudent()
                    .stream()
                    .map(Student::getIdStudent)
                    .toList();
            List<Student> studentsProvisional = studentRepository.findAll()
                    .stream()
                    .filter(student -> {
                        boolean check = false;
                        for (String idStudent: idStudentOriginal) {
                            if (student.getIdStudent().equals(idStudent)) {
                                check = true;
                            }
                        }
                        return check;
                    }).toList();
            studentsProvisional.forEach(student -> student.getAlumnosEstudios().remove(asignaturaProvisional));
            studentsProvisional.forEach(student -> studentRepository.save(student));
            Student student1 = studentRepository.findById(asignatura.getIdStudent())
                    .orElseThrow(EntityNotFoundException::new);
            asignatura1.getStudent().add(student1);
            student1.getAlumnosEstudios().add(asignatura1);
            studentRepository.save(student1);
        } else {
            asignatura1.setStudent(asignaturaProvisional.getStudent());
        }
        asignaturaRepository.save(asignatura1);
        return mapper.asignaturaToAsignaturaOutputDto(asignatura1);
    }

    @Override
    public void deleteAsignatura(String id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow();
        List<Student> students = asignaturaRepository.findById(id).orElseThrow().getStudent();
        students.forEach(student -> student.getAlumnosEstudios().remove(asignatura));
        students.forEach(student -> studentRepository.save(student));
        asignaturaRepository.deleteById(id);
    }
}
