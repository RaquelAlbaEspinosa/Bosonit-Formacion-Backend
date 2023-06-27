package com.bosonit.formacion.block7crudvalidation.asignatura.application;

import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceslmpl implements AsignaturaServices {
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(@Valid AsignaturaInputDto asignatura) {
        Student studentProvisional = studentRepository.findById(asignatura.getIdStudent())
                .orElseThrow(EntityNotFoundException::new);
        Asignatura asignatura1 = new Asignatura(asignatura);
        studentProvisional.getAlumnosEstudios().add(asignatura1);
        asignatura1.setStudent(studentProvisional);
        return asignaturaRepository.save(asignatura1).asignaturaToAsignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto getAsignaturaById(String id) {
        return asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new)
                .asignaturaToAsignaturaOutputDto();
    }

    @Override
    public Iterable<AsignaturaOutputDto> getAllAsignatura(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return asignaturaRepository.findAll(pageRequest).getContent().stream()
                .map(Asignatura::asignaturaToAsignaturaOutputDto).toList();
    }
    @Override
    public Iterable<AsignaturaOutputDto> getAsignaturaByStudentId (String idStudent){
        return asignaturaRepository.findAll().stream()
                .filter(asignatura -> asignatura.getStudent().getIdStudent().equals(idStudent))
                .map(Asignatura::asignaturaToAsignaturaOutputDto).toList();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(@Valid AsignaturaInputDto asignatura, String id) {
        Asignatura asignaturaProvisional = asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        asignatura.setComments(asignatura.getComments() != null ?
                asignatura.getComments() : asignaturaProvisional.getComments());
        Asignatura asignatura1 = new Asignatura(asignatura);
        asignatura1.setIdStudy(asignaturaProvisional.getIdStudy());
        if (asignatura.getIdStudent() != null){
            String idStudentOriginal = asignaturaProvisional.getStudent().getIdStudent();
            Student studentProvisional = studentRepository.findById(idStudentOriginal)
                    .orElseThrow(EntityNotFoundException::new);
            studentProvisional.getAlumnosEstudios().remove(asignaturaProvisional);
            studentProvisional.setIdStudent(studentProvisional.getIdStudent());
            Student student1 = studentRepository.findById(asignatura.getIdStudent())
                    .orElseThrow(EntityNotFoundException::new);
            asignatura1.setStudent(student1);
            student1.getAlumnosEstudios().add(asignatura1);
        } else {
            asignatura1.setStudent(asignaturaProvisional.getStudent());
        }
        return asignaturaRepository.save(asignatura1).asignaturaToAsignaturaOutputDto();
    }

    @Override
    public void deleteAsignatura(String id) {
        Student studentProvisional = studentRepository.findById(asignaturaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new).getStudent().getIdStudent())
                .orElseThrow(EntityNotFoundException::new);
        studentProvisional.getAlumnosEstudios().remove(asignaturaRepository
                .findById(id).orElseThrow(EntityNotFoundException::new));
        asignaturaRepository.deleteById(id);
    }
}
