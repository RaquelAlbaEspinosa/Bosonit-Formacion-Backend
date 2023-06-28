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

@Service
public class AsignaturaServiceslmpl implements AsignaturaServices {
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(@Valid AsignaturaInputDto asignatura) {
        AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
        Student studentProvisional = studentRepository.findById(asignatura.getIdStudent())
                .orElseThrow(EntityNotFoundException::new);
        Asignatura asignatura1 = mapper.asignaturaInputDtoToAsignatura(asignatura);
        studentProvisional.getAlumnosEstudios().add(asignatura1);
        asignatura1.setStudent(studentProvisional);
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
        return asignaturaRepository.findAll().stream()
                .filter(asignatura -> asignatura.getStudent().getIdStudent().equals(idStudent))
                .map(asignatura -> {
                    AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
                    return mapper.asignaturaToAsignaturaOutputDto(asignatura);
                }).toList();
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(@Valid AsignaturaInputDto asignatura, String id) {
        AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);
        Asignatura asignaturaProvisional = asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        asignatura.setComments(asignatura.getComments() != null ?
                asignatura.getComments() : asignaturaProvisional.getComments());
        Asignatura asignatura1 = mapper.asignaturaInputDtoToAsignatura(asignatura);
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
        asignaturaRepository.save(asignatura1);
        return mapper.asignaturaToAsignaturaOutputDto(asignatura1);
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
