package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.asignatura.application.AsignaturaServices;
import com.bosonit.formacion.block7crudvalidation.asignatura.application.AsignaturaServiceslmpl;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.AsignaturaControllers;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper.AsignaturaMapper;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.student.application.StudentService;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AsignaturaServiceControllerMockTest {
    public Persona persona;
    @Mock
    public PersonaRepository personaRepository;
    public Student student;
    public StudentMapper studentMapper;
    public List<Student> students;
    @Mock
    public StudentRepository studentRepository;
    @Mock
    public StudentService studentServiceMock;
    public StudentOutputDto studentOutputDto;
    public Asignatura asignatura;
    public AsignaturaInputDto asignaturaInputDto;
    public AsignaturaOutputDto asignaturaOutputDto;
    public AsignaturaMapper asignaturaMapper;
    public Iterable<AsignaturaOutputDto> asignaturasOutputDto;
    public List<Asignatura> asignaturas;
    @Mock
    public AsignaturaServices asignaturaServicesMock;
    @Mock
    public AsignaturaRepository asignaturaRepository;
    @InjectMocks
    public AsignaturaControllers asignaturaControllers;
    @InjectMocks
    public AsignaturaServiceslmpl asignaturaServices;
    @BeforeEach
    void runBefore(){
        //se recargan los Mocks de Mockito
        MockitoAnnotations.initMocks(this);
        //se crea y regista en el repo la persona asociada al estudiante
        this.persona = new Persona(new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date()));
        this.personaRepository.save(this.persona);
        //se da valores a los student, studentInput y studentOutput y se guarda en el repo
        this.studentMapper = Mappers.getMapper(StudentMapper.class);
        this.student = this.studentMapper.studentInputDtoToStudent(new StudentInputDto("1234",40, "fantástico",
                "back", 1, "1234"));
        this.studentOutputDto = this.studentMapper.studentToStudentOutputDto(this.student);
        this.studentRepository.save(this.student);
        //se da valor a la asignatura y se guarda
        this.asignaturaMapper = Mappers.getMapper(AsignaturaMapper.class);
        this.asignaturaInputDto = new AsignaturaInputDto("1234", null, "Java",
                "Interesante", new Date(), new Date());
        this.asignatura = this.asignaturaMapper.asignaturaInputDtoToAsignatura(this.asignaturaInputDto);
        this.asignaturaOutputDto = this.asignaturaMapper.asignaturaToAsignaturaOutputDto(this.asignatura);
        //se añade un estudiante a la lista de estudiantes
        this.students = new ArrayList<>();
        this.students.add(student);
        this.asignatura.setStudent(students);
        this.asignaturaRepository.save(asignatura);
        //se añade la asignaturaOutput al Iterable
        List<AsignaturaOutputDto> asignaturaOutputDto1 = new ArrayList<>();
        asignaturaOutputDto1.add(asignaturaOutputDto);
        this.asignaturasOutputDto = asignaturaOutputDto1;
        //se añade una asignatura a la lista de asignaturas
        this.asignaturas = new ArrayList<>();
        this.asignaturas.add(asignatura);
        this.student.setAlumnosEstudios(asignaturas);
    }
    @Test
    void testAddAsignatura(){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));

        AsignaturaOutputDto result = asignaturaServices.addAsignatura(asignaturaInputDto);
        assertEquals(asignaturaOutputDto.getIdStudy(), result.getIdStudy());
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), result.getNombreAsignatura());
        assertEquals(asignaturaOutputDto.getComments(), result.getComments());
        assertEquals(asignaturaOutputDto.getInitialDate(), result.getInitialDate());
        assertEquals(asignaturaOutputDto.getFinishDate(), result.getFinishDate());

        when(asignaturaServicesMock.addAsignatura(asignaturaInputDto)).thenReturn(asignaturaOutputDto);
        ResponseEntity<AsignaturaOutputDto> responseEntity = asignaturaControllers.addAsignatura(asignaturaInputDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(asignaturaOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetAsignaturaById (){
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));
        AsignaturaOutputDto result = asignaturaServices.getAsignaturaById("1234");
        assertEquals(asignaturaOutputDto.getIdStudy(), result.getIdStudy());
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), result.getNombreAsignatura());
        assertEquals(asignaturaOutputDto.getComments(), result.getComments());
        assertEquals(asignaturaOutputDto.getInitialDate(), result.getInitialDate());
        assertEquals(asignaturaOutputDto.getFinishDate(), result.getFinishDate());

        when(asignaturaServicesMock.getAsignaturaById("1234")).thenReturn(asignaturaOutputDto);
        ResponseEntity<AsignaturaOutputDto> responseEntity = asignaturaControllers.getAsignaturaById("1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(asignaturaOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetAsignaturaByStudentId () {
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        Iterable<AsignaturaOutputDto> result = asignaturaServices.getAsignaturaByStudentId("1234");
        for(AsignaturaOutputDto asignatura1 : result){
            assertEquals(asignaturaOutputDto.getIdStudy(), asignatura1.getIdStudy());
            assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura1.getNombreAsignatura());
            assertEquals(asignaturaOutputDto.getComments(), asignatura1.getComments());
            assertEquals(asignaturaOutputDto.getInitialDate(), asignatura1.getInitialDate());
            assertEquals(asignaturaOutputDto.getFinishDate(), asignatura1.getFinishDate());
        }

        when(asignaturaServicesMock.getAsignaturaByStudentId("1234")).thenReturn(asignaturasOutputDto);
        when(studentServiceMock.getStudentById("1234")).thenReturn(studentOutputDto);
        Iterable<AsignaturaOutputDto> resultController = asignaturaControllers.getAsignaturaByStudentId("1234");
        for(AsignaturaOutputDto asignatura1 : resultController){
            assertEquals(asignaturaOutputDto.getIdStudy(), asignatura1.getIdStudy());
            assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura1.getNombreAsignatura());
            assertEquals(asignaturaOutputDto.getComments(), asignatura1.getComments());
            assertEquals(asignaturaOutputDto.getInitialDate(), asignatura1.getInitialDate());
            assertEquals(asignaturaOutputDto.getFinishDate(), asignatura1.getFinishDate());
        }
    }
    @Test
    void testGetAllAsignatura(){
        Page<Asignatura> asignaturaPage = new PageImpl<>(asignaturas);
        when(asignaturaRepository.findAll(PageRequest.of(0,4))).thenReturn(asignaturaPage);
        Iterable<AsignaturaOutputDto> result = asignaturaServices.getAllAsignatura(0,4);
        for(AsignaturaOutputDto asignatura : result){
            assertEquals(asignaturaOutputDto.getIdStudy(), asignatura.getIdStudy());
            assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura.getNombreAsignatura());
            assertEquals(asignaturaOutputDto.getComments(), asignatura.getComments());
            assertEquals(asignaturaOutputDto.getInitialDate(), asignatura.getInitialDate());
            assertEquals(asignaturaOutputDto.getFinishDate(), asignatura.getFinishDate());
        }

        when(asignaturaServicesMock.getAllAsignatura(0, 4)).thenReturn(asignaturasOutputDto);
        Iterable<AsignaturaOutputDto> resultController = asignaturaControllers.getAsignaturaByStudentId("1234");
        for(AsignaturaOutputDto asignatura1 : resultController){
            assertEquals(asignaturaOutputDto.getIdStudy(), asignatura1.getIdStudy());
            assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura1.getNombreAsignatura());
            assertEquals(asignaturaOutputDto.getComments(), asignatura1.getComments());
            assertEquals(asignaturaOutputDto.getInitialDate(), asignatura1.getInitialDate());
            assertEquals(asignaturaOutputDto.getFinishDate(), asignatura1.getFinishDate());
        }
    }
    @Test
    void testUpdateAsignatura(){
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));
        studentRepository.findById("1234").orElseThrow()
                .setPersona(personaRepository.findById(1).orElseThrow());

        AsignaturaOutputDto result = asignaturaServices.updateAsignatura(asignaturaInputDto, "1234");
        assertEquals(asignaturaOutputDto.getIdStudy(), result.getIdStudy());
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), result.getNombreAsignatura());
        assertEquals(asignaturaOutputDto.getComments(), result.getComments());
        assertEquals(asignaturaOutputDto.getInitialDate(), result.getInitialDate());
        assertEquals(asignaturaOutputDto.getFinishDate(), result.getFinishDate());

        when(asignaturaServicesMock.updateAsignatura(asignaturaInputDto,"1234")).thenReturn(asignaturaOutputDto);
        ResponseEntity<AsignaturaOutputDto> responseEntity = asignaturaControllers.updateAsignatura(asignaturaInputDto,"1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(asignaturaOutputDto, responseEntity.getBody());
    }
    @Test
    void testDeleteAsignatura(){
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));
        asignaturaServices.deleteAsignatura("1234");
        verify(asignaturaRepository).deleteById("1234");

        doNothing().when(asignaturaServicesMock).deleteAsignatura("1234");
        ResponseEntity<String> responseEntity = asignaturaControllers.deleteAsignatura("1234");
        verify(asignaturaServicesMock, times(1)).deleteAsignatura("1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se ha borrado la asignatura con id: 1234", responseEntity.getBody());
    }
}
