package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.asignatura.application.AsignaturaServices;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper.AsignaturaMapper;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper.ProfesorMapper;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.application.StudentService;
import com.bosonit.formacion.block7crudvalidation.student.application.StudentServicelmpl;
import com.bosonit.formacion.block7crudvalidation.student.controller.ControllersStudent;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceControllerMockTest {
    @Mock
    public PersonaRepository personaRepository;
    public Persona persona;
    @Mock
    public ProfesorRepository profesorRepository;
    public Profesor profesor;
    public ProfesorMapper profesorMapper;
    @Mock
    public AsignaturaRepository asignaturaRepository;
    @Mock
    public AsignaturaServices asignaturaServicesMock;
    public Asignatura asignatura;
    public AsignaturaOutputDto asignaturaOutputDto;
    public AsignaturaMapper asignaturaMapper;
    public Student student;
    public StudentInputDto studentInputDto;
    public StudentOutputDto studentOutputDto;
    public StudentFullOutputDto studentFullOutputDto;
    public StudentMapper studentMapper;
    public Iterable<StudentOutputDto> studentsOutputDto;
    public List<Student> students;
    @Mock
    public StudentService studentServiceMock;
    @Mock
    public StudentRepository studentRepository;
    @InjectMocks
    public ControllersStudent controllersStudent;
    @InjectMocks
    public StudentServicelmpl studentService;
    @BeforeEach
    public void runBefore(){
        //se recargan los Mocks de Mockito
        MockitoAnnotations.initMocks(this);
        //se crea y regista en el repo la persona asociada al estudiante
        this.persona = new Persona(new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date()));
        this.personaRepository.save(this.persona);
        //se crea y registra el profesor asociado al alumno
        this.profesorMapper = Mappers.getMapper(ProfesorMapper.class);
        this.profesor = this.profesorMapper
                .profesorInputDtoProfesor(new ProfesorInputDto("1234","bien, todo bien",
                        "back", 1));
        this.profesorRepository.save(this.profesor);
        //se da valores a los student, studentInput y studentOutput y se guarda en el repo
        this.studentInputDto = new StudentInputDto("1234",40, "fantástico",
                "back", 1, "1234");
        this.studentMapper = Mappers.getMapper(StudentMapper.class);
        this.student = this.studentMapper.studentInputDtoToStudent(this.studentInputDto);
        this.studentOutputDto = this.studentMapper.studentToStudentOutputDto(this.student);
        this.studentRepository.save(this.student);
        //se añade eñ studentOutput al Iterable
        List<StudentOutputDto> studentOutputDto1 = new ArrayList<>();
        studentOutputDto1.add(studentOutputDto);
        this.studentsOutputDto = studentOutputDto1;
        //se añade un student a la lista de students
        this.students = new ArrayList<>();
        this.students.add(student);
        this.profesor.setStudents(students);
        //se da valores a la asignatura y se guarda
        this.asignaturaMapper = Mappers.getMapper(AsignaturaMapper.class);
        this.asignatura = asignaturaMapper.asignaturaInputDtoToAsignatura(new AsignaturaInputDto("1234",
                null, "Java", "Interesante", new Date(), new Date()));
        this.asignaturaOutputDto = this.asignaturaMapper.asignaturaToAsignaturaOutputDto(this.asignatura);
        this.asignaturaRepository.save(asignatura);
        this.asignatura.setStudent(new ArrayList<>());
        this.student.setAlumnosEstudios(new ArrayList<>());
    }
    @Test
    void testAddStudent(){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));

        StudentOutputDto result = studentService.addStudent(studentInputDto);
        assertEquals(studentOutputDto.getIdStudent(), result.getIdStudent());
        assertEquals(studentOutputDto.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentOutputDto.getComments(), result.getComments());
        assertEquals(studentOutputDto.getBranch(), result.getBranch());
        assertEquals(studentOutputDto.getClass(), result.getClass());

        when(studentServiceMock.addStudent(studentInputDto)).thenReturn(studentOutputDto);
        ResponseEntity<StudentOutputDto> responseEntity = controllersStudent.addStudent(studentInputDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(studentOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetStudentById (){
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        StudentOutputDto result = studentService.getStudentById("1234");
        assertEquals(studentOutputDto.getIdStudent(), result.getIdStudent());
        assertEquals(studentOutputDto.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentOutputDto.getComments(), result.getComments());
        assertEquals(studentOutputDto.getBranch(), result.getBranch());
        assertEquals(studentOutputDto.getClass(), result.getClass());

        when(studentServiceMock.getStudentById("1234")).thenReturn(studentOutputDto);
        ResponseEntity<StudentOutputDto> responseEntity = controllersStudent.getStudentById("1234", "simple");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetStudentByIdFull (){
        this.studentFullOutputDto = this.studentMapper.studentToStudentFullOutputDto(student);
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        StudentFullOutputDto result = studentService.getStudentByIdFull("1234");
        assertEquals(studentOutputDto.getIdStudent(), result.getIdStudent());
        assertEquals(studentOutputDto.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentOutputDto.getComments(), result.getComments());
        assertEquals(studentOutputDto.getBranch(), result.getBranch());
        assertEquals(studentFullOutputDto.getIdPersona(), result.getIdPersona());
        assertEquals(studentFullOutputDto.getUsuario(), result.getUsuario());
        assertEquals(studentFullOutputDto.getPassword(), result.getPassword());
        assertEquals(studentFullOutputDto.getName(), result.getName());
        assertEquals(studentFullOutputDto.getSurname(), result.getSurname());
        assertEquals(studentFullOutputDto.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(studentFullOutputDto.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(studentFullOutputDto.getCity(), result.getCity());
        assertEquals(studentFullOutputDto.getActive(), result.getActive());
        assertEquals(studentFullOutputDto.getCreatedDate(), result.getCreatedDate());
        assertEquals(studentFullOutputDto.getImageUrl(), result.getImageUrl());
        assertEquals(studentFullOutputDto.getTerminationDate(), result.getTerminationDate());

        when(studentServiceMock.getStudentByIdFull("1234")).thenReturn(studentFullOutputDto);
        ResponseEntity<StudentFullOutputDto> responseEntity = controllersStudent.getStudentById("1234", "full");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentFullOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetStudentByUsuario(){
        when(studentRepository.findAll().stream()
                .filter(persona1 -> persona.getUsuario().equals("RaquelAlba")).toList())
                .thenReturn(students);
        Iterable<StudentOutputDto> result = studentService.getStudentByUsuario("RaquelAlba");
        for(StudentOutputDto student : result){
            assertEquals(studentOutputDto.getIdStudent(), student.getIdStudent());
            assertEquals(studentOutputDto.getNumHoursWeek(), student.getNumHoursWeek());
            assertEquals(studentOutputDto.getComments(), student.getComments());
            assertEquals(studentOutputDto.getBranch(), student.getBranch());
            assertEquals(studentOutputDto.getClass(), student.getClass());
        }

        when(studentServiceMock.getStudentByUsuario("RaquelAlba")).thenReturn(studentsOutputDto);
        Iterable<StudentOutputDto> response = controllersStudent.getStudentByUsuario("RaquelAlba");
        assertEquals(studentsOutputDto, response);
    }
    @Test
    void testGetAllStudent(){
        Page<Student> studentPage = new PageImpl<>(students);
        when(studentRepository.findAll(PageRequest.of(0,4))).thenReturn(studentPage);
        Iterable<StudentOutputDto> result = studentService.getAllStudent(0,4);
        for(StudentOutputDto student : result){
            assertEquals(studentOutputDto.getIdProfesor(), student.getIdProfesor());
            assertEquals(studentOutputDto.getNumHoursWeek(), student.getNumHoursWeek());
            assertEquals(studentOutputDto.getComments(), student.getComments());
            assertEquals(studentOutputDto.getBranch(), student.getBranch());
            assertEquals(studentOutputDto.getClass(), student.getClass());
        }

        when(studentServiceMock.getAllStudent(0, 4)).thenReturn(studentsOutputDto);
        Iterable<StudentOutputDto> response = controllersStudent.getAllStudent(0, 4);
        assertEquals(studentsOutputDto, response);
    }
    @Test
    void testUpdateStudent(){
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        studentRepository.findById("1234").orElseThrow()
                .setPersona(personaRepository.findById(1).orElseThrow());
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        studentRepository.findById("1234").orElseThrow()
                .setProfesor(profesorRepository.findById("1234").orElseThrow());

        StudentOutputDto result = studentService.updateStudent(studentInputDto, "1234");
        assertEquals(studentOutputDto.getIdStudent(), result.getIdStudent());
        assertEquals(studentOutputDto.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentOutputDto.getComments(), result.getComments());
        assertEquals(studentOutputDto.getBranch(), result.getBranch());
        assertEquals(studentOutputDto.getClass(), result.getClass());

        when(studentServiceMock.updateStudent(studentInputDto,"1234")).thenReturn(studentOutputDto);
        ResponseEntity<StudentOutputDto> responseEntity = controllersStudent.updateStudent(studentInputDto, "1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentOutputDto, responseEntity.getBody());
    }
    @Test
    void testDeleteStudent(){
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        studentService.deleteStudent("1234");
        verify(studentRepository).delete(student);

        doNothing().when(studentServiceMock).deleteStudent("1234");
        ResponseEntity<String> responseEntity = controllersStudent.deleteStudent("1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se ha borrado el estudiante con id: 1234", responseEntity.getBody());
    }
    @Test
    void testAddAsignaturaToStudent () {
        when(asignaturaRepository.findById("1234")).thenReturn(Optional.ofNullable(asignatura));
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        studentService.addAsignaturaToStudent("1234", "1234");
        assertTrue(student.getAlumnosEstudios().contains(asignatura));
        assertTrue(asignatura.getStudent().contains(student));

        when(studentServiceMock.getStudentById("1234")).thenReturn(studentOutputDto);
        when(asignaturaServicesMock.getAsignaturaById("1234")).thenReturn(asignaturaOutputDto);
        ResponseEntity<String> responseEntity = controllersStudent.addAsignaturaToStudent("1234", "1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se ha añadido la asignatura con id 1234 al estudiante con id 1234", responseEntity.getBody());
    }
}
