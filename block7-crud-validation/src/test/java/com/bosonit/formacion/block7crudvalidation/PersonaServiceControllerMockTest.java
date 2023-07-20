package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.persona.application.PersonaServices;
import com.bosonit.formacion.block7crudvalidation.persona.application.PersonaServiceslmpl;
import com.bosonit.formacion.block7crudvalidation.persona.controller.ControllersPersona;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.mapper.PersonaMapper;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper.ProfesorMapper;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;



class PersonaServiceControllerMockTest {
    public Persona persona;
    public PersonaInputDto personaInputDto;
    public PersonaOutputDto personaOutputDto;
    public PersonaEstudianteOutputDto personaEstudianteOutputDto;
    public PersonaProfesorOutputDto personaProfesorOutputDto;
    @Mock
    public StudentRepository studentRepository;
    public Student student;
    public StudentMapper studentMapper;
    @Mock
    public ProfesorRepository profesorRepository;
    public Profesor profesor;
    public ProfesorMapper profesorMapper;
    public PersonaMapper personaMapper;
    public Iterable<PersonaOutputDto> personasOutputDto;
    public List<Persona> personas;
    @Mock
    public PersonaServices personaServicesMock;
    @Mock
    public PersonaRepository personaRepository;
    @InjectMocks
    public ControllersPersona controllersPersona;
    @InjectMocks
    public PersonaServiceslmpl personaServices;
    @BeforeEach
    public void runBefore(){
        //se recargan los Mocks de Mockito
        MockitoAnnotations.initMocks(this);
        //damos valor y guardamos en el repo a la persona
        this.personaInputDto = new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date());
        this.persona = new Persona(this.personaInputDto);
        this.personaMapper = Mappers.getMapper(PersonaMapper.class);
        this.personaOutputDto = this.personaMapper.personaToPersonaOutputDto(this.persona);
        this.personaRepository.save(this.persona);
        //añadimos una personaOutput al Iterable
        List<PersonaOutputDto> personasOutputDto1 = new ArrayList<>();
        personasOutputDto1.add(personaOutputDto);
        this.personasOutputDto = personasOutputDto1;
        //añadimos una persona a la lista
        this.personas = new ArrayList<>();
        this.personas.add(persona);
        //damos valor y guardamos el student
        this.studentMapper = Mappers.getMapper(StudentMapper.class);
        this.student = this.studentMapper.studentInputDtoToStudent(new StudentInputDto("1234",40, "fantástico",
                "back", 1, "1234"));
        this.studentRepository.save(student);
        //damos valor y guardamos el profesor
        this.profesorMapper = Mappers.getMapper(ProfesorMapper.class);
        this.profesor = this.profesorMapper
                .profesorInputDtoProfesor(new ProfesorInputDto("1234","bien, todo bien",
                        "back", 1));
        this.profesorRepository.save(profesor);
    }
    @Test
    void testAddPersona(){
        PersonaOutputDto resultado =  personaServices.addPersona(personaInputDto);
        assertEquals(personaOutputDto.getIdPersona(), resultado.getIdPersona());
        assertEquals(personaOutputDto.getUsuario(), resultado.getUsuario());
        assertEquals(personaOutputDto.getName(), resultado.getName());
        assertEquals(personaOutputDto.getSurname(), resultado.getSurname());
        assertEquals(personaOutputDto.getPassword(), resultado.getPassword());
        assertEquals(personaOutputDto.getCity(), resultado.getCity());
        assertEquals(personaOutputDto.getCompanyEmail(), resultado.getCompanyEmail());
        assertEquals(personaOutputDto.getPersonalEmail(), resultado.getPersonalEmail());
        assertEquals(personaOutputDto.getActive(), resultado.getActive());
        assertEquals(personaOutputDto.getCreatedDate(), resultado.getCreatedDate());
        assertEquals(personaOutputDto.getTerminationDate(), resultado.getTerminationDate());
        assertEquals(personaOutputDto.getImageUrl(), resultado.getImageUrl());

        when(personaServicesMock.addPersona(personaInputDto)).thenReturn(personaOutputDto);
        ResponseEntity<PersonaOutputDto> responseEntity = controllersPersona.addPersona(personaInputDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(personaOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetPersonaById (){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        PersonaOutputDto resultado = personaServices.getPersonaById(1);
        assertEquals(personaOutputDto.getIdPersona(), resultado.getIdPersona());
        assertEquals(personaOutputDto.getUsuario(), resultado.getUsuario());
        assertEquals(personaOutputDto.getName(), resultado.getName());
        assertEquals(personaOutputDto.getSurname(), resultado.getSurname());
        assertEquals(personaOutputDto.getPassword(), resultado.getPassword());
        assertEquals(personaOutputDto.getCity(), resultado.getCity());
        assertEquals(personaOutputDto.getCompanyEmail(), resultado.getCompanyEmail());
        assertEquals(personaOutputDto.getPersonalEmail(), resultado.getPersonalEmail());
        assertEquals(personaOutputDto.getActive(), resultado.getActive());
        assertEquals(personaOutputDto.getCreatedDate(), resultado.getCreatedDate());
        assertEquals(personaOutputDto.getTerminationDate(), resultado.getTerminationDate());
        assertEquals(personaOutputDto.getImageUrl(), resultado.getImageUrl());

        when(personaServicesMock.getPersonaById(1)).thenReturn(personaOutputDto);
        when(personaServicesMock.getTypeOfPersona(1)).thenReturn("none");
        ResponseEntity<PersonaOutputDto> responseEntity1 = controllersPersona.getPersonaById(1, "simple");
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
        assertEquals(personaOutputDto, responseEntity1.getBody());

        when(personaServicesMock.getPersonaByIdEstudiante(1)).thenReturn(personaEstudianteOutputDto);
        when(personaServicesMock.getTypeOfPersona(1)).thenReturn("Estudiante");
        ResponseEntity<PersonaEstudianteOutputDto> responseEntity = controllersPersona.getPersonaById(1, "full");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personaEstudianteOutputDto, responseEntity.getBody());

        when(personaServicesMock.getPersonaByIdProfesor(1)).thenReturn(personaProfesorOutputDto);
        when(personaServicesMock.getPersonaById(1)).thenReturn(personaOutputDto);
        ResponseEntity<PersonaProfesorOutputDto> responseEntity2 = controllersPersona.getPersonaById(1, "full");
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals(personaProfesorOutputDto, responseEntity2.getBody());
    }
    @Test
    void testGetPersonaByIdEstudiante () {
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(studentRepository.findById("1234")).thenReturn(Optional.ofNullable(student));
        this.persona.setStudent(student);
        this.personaEstudianteOutputDto = this.personaMapper.personaToPersonaEstudianteOutputDto(persona);
        assertEquals(personaEstudianteOutputDto.getIdPersona(), personaServices.getPersonaByIdEstudiante(1).getIdPersona());
        assertEquals(personaEstudianteOutputDto.getUsuario(), personaServices.getPersonaByIdEstudiante(1).getUsuario());
        assertEquals(personaEstudianteOutputDto.getName(), personaServices.getPersonaByIdEstudiante(1).getName());
        assertEquals(personaEstudianteOutputDto.getSurname(), personaServices.getPersonaByIdEstudiante(1).getSurname());
        assertEquals(personaEstudianteOutputDto.getPassword(), personaServices.getPersonaByIdEstudiante(1).getPassword());
        assertEquals(personaEstudianteOutputDto.getCity(), personaServices.getPersonaByIdEstudiante(1).getCity());
        assertEquals(personaEstudianteOutputDto.getCompanyEmail(), personaServices.getPersonaByIdEstudiante(1).getCompanyEmail());
        assertEquals(personaEstudianteOutputDto.getPersonalEmail(), personaServices.getPersonaByIdEstudiante(1).getPersonalEmail());
        assertEquals(personaEstudianteOutputDto.getActive(), personaServices.getPersonaByIdEstudiante(1).getActive());
        assertEquals(personaEstudianteOutputDto.getCreatedDate(), personaServices.getPersonaByIdEstudiante(1).getCreatedDate());
        assertEquals(personaEstudianteOutputDto.getTerminationDate(), personaServices.getPersonaByIdEstudiante(1).getTerminationDate());
        assertEquals(personaEstudianteOutputDto.getImageUrl(), personaServices.getPersonaByIdEstudiante(1).getImageUrl());
        assertEquals(personaEstudianteOutputDto.getIdStudent(), personaServices.getPersonaByIdEstudiante(1).getIdStudent());
        assertEquals(personaEstudianteOutputDto.getBranch(), personaServices.getPersonaByIdEstudiante(1).getBranch());
        assertEquals(personaEstudianteOutputDto.getComments(), personaServices.getPersonaByIdEstudiante(1).getComments());
        assertEquals(personaEstudianteOutputDto.getNumHoursWeek(), personaServices.getPersonaByIdEstudiante(1).getNumHoursWeek());
    }
    @Test
    void testGetPersonaByIdProfesor () {
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        this.persona.setProfesor(profesor);
        this.personaProfesorOutputDto = this.personaMapper.personaToPersonaProfesorOutputDto(persona);
        assertEquals(personaProfesorOutputDto.getIdPersona(), personaServices.getPersonaByIdProfesor(1).getIdPersona());
        assertEquals(personaProfesorOutputDto.getUsuario(), personaServices.getPersonaByIdProfesor(1).getUsuario());
        assertEquals(personaProfesorOutputDto.getName(), personaServices.getPersonaByIdProfesor(1).getName());
        assertEquals(personaProfesorOutputDto.getSurname(), personaServices.getPersonaByIdProfesor(1).getSurname());
        assertEquals(personaProfesorOutputDto.getPassword(), personaServices.getPersonaByIdProfesor(1).getPassword());
        assertEquals(personaProfesorOutputDto.getCity(), personaServices.getPersonaByIdProfesor(1).getCity());
        assertEquals(personaProfesorOutputDto.getCompanyEmail(), personaServices.getPersonaByIdProfesor(1).getCompanyEmail());
        assertEquals(personaProfesorOutputDto.getPersonalEmail(), personaServices.getPersonaByIdProfesor(1).getPersonalEmail());
        assertEquals(personaProfesorOutputDto.getActive(), personaServices.getPersonaByIdProfesor(1).getActive());
        assertEquals(personaProfesorOutputDto.getCreatedDate(), personaServices.getPersonaByIdProfesor(1).getCreatedDate());
        assertEquals(personaProfesorOutputDto.getTerminationDate(), personaServices.getPersonaByIdProfesor(1).getTerminationDate());
        assertEquals(personaProfesorOutputDto.getImageUrl(), personaServices.getPersonaByIdProfesor(1).getImageUrl());
        assertEquals(personaProfesorOutputDto.getIdProfesor(), personaServices.getPersonaByIdProfesor(1).getIdProfesor());
        assertEquals(personaProfesorOutputDto.getBranch(), personaServices.getPersonaByIdProfesor(1).getBranch());
        assertEquals(personaProfesorOutputDto.getComments(), personaServices.getPersonaByIdProfesor(1).getComments());
    }

    @Test
    void testGetPersonaByUsuario(){
        when(personaRepository.findAll().stream()
                .filter(persona1 -> persona.getUsuario().equals("RaquelAlba")).toList())
                .thenReturn(personas);
        Iterable<PersonaOutputDto> resultado =  personaServices.getPersonaByUsuario("RaquelAlba");
        for (PersonaOutputDto persona1 : resultado) {
            assertEquals(personaOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaOutputDto.getName(), persona1.getName());
            assertEquals(personaOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaOutputDto.getCity(), persona1.getCity());
            assertEquals(personaOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaOutputDto.getActive(), persona1.getActive());
            assertEquals(personaOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaOutputDto.getImageUrl(), persona1.getImageUrl());
        }

        when(personaServicesMock.getPersonaByUsuario("RaquelAlba")).thenReturn(personasOutputDto);
        Iterable<PersonaOutputDto> response = controllersPersona.getPersonaByUsuario("RaquelAlba", "simple");
        assertIterableEquals(personasOutputDto, response);

        when(personaServicesMock.getPersonaByUsuarioFull("RaquelAlba")).thenReturn(personasOutputDto);
        Iterable<PersonaOutputDto> response2 = controllersPersona.getPersonaByUsuario("RaquelAlba", "full");
        assertIterableEquals(personasOutputDto, response2);
    }
    @Test
    void testGetPersonaByUsuarioFullEstudiante(){
        this.persona.setStudent(this.student);
        this.personaEstudianteOutputDto = this.personaMapper.personaToPersonaEstudianteOutputDto(persona);
        when(personaRepository.findAll().stream()
                .filter(persona1 -> persona.getUsuario().equals("RaquelAlba")).toList())
                .thenReturn(personas);
        Iterable<PersonaEstudianteOutputDto> resultado = personaServices.getPersonaByUsuarioFull("RaquelAlba");
        for (PersonaEstudianteOutputDto persona1 : resultado) {
            assertEquals(personaEstudianteOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaEstudianteOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaEstudianteOutputDto.getName(), persona1.getName());
            assertEquals(personaEstudianteOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaEstudianteOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaEstudianteOutputDto.getCity(), persona1.getCity());
            assertEquals(personaEstudianteOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaEstudianteOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaEstudianteOutputDto.getActive(), persona1.getActive());
            assertEquals(personaEstudianteOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaEstudianteOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaEstudianteOutputDto.getImageUrl(), persona1.getImageUrl());
            assertEquals(personaEstudianteOutputDto.getIdStudent(), persona1.getIdStudent());
            assertEquals(personaEstudianteOutputDto.getBranch(), persona1.getBranch());
            assertEquals(personaEstudianteOutputDto.getComments(), persona1.getComments());
            assertEquals(personaEstudianteOutputDto.getNumHoursWeek(), persona1.getNumHoursWeek());
        }
    }
    @Test
    void testGetPersonaByUsuarioFullProfesor(){
        this.persona.setProfesor(this.profesor);
        this.personaProfesorOutputDto = this.personaMapper.personaToPersonaProfesorOutputDto(persona);
        when(personaRepository.findAll().stream()
                .filter(persona1 -> persona.getUsuario().equals("RaquelAlba")).toList())
                .thenReturn(personas);
        Iterable<PersonaProfesorOutputDto> resultado = personaServices.getPersonaByUsuarioFull("RaquelAlba");
        for (PersonaProfesorOutputDto persona1 : resultado) {
            assertEquals(personaProfesorOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaProfesorOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaProfesorOutputDto.getName(), persona1.getName());
            assertEquals(personaProfesorOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaProfesorOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaProfesorOutputDto.getCity(), persona1.getCity());
            assertEquals(personaProfesorOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaProfesorOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaProfesorOutputDto.getActive(), persona1.getActive());
            assertEquals(personaProfesorOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaProfesorOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaProfesorOutputDto.getImageUrl(), persona1.getImageUrl());
            assertEquals(personaProfesorOutputDto.getIdProfesor(), persona1.getIdProfesor());
            assertEquals(personaProfesorOutputDto.getBranch(), persona1.getBranch());
            assertEquals(personaProfesorOutputDto.getComments(), persona1.getComments());
        }
    }
    @Test
    void testGetAllPersona(){
        Page<Persona> personasPage = new PageImpl<>(personas);
        when(personaRepository.findAll(PageRequest.of(0,4))).thenReturn(personasPage);
        Iterable<PersonaOutputDto> resultado = personaServices.getAllPersona(0, 4);
        for (PersonaOutputDto persona1 : resultado) {
            assertEquals(personaOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaOutputDto.getName(), persona1.getName());
            assertEquals(personaOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaOutputDto.getCity(), persona1.getCity());
            assertEquals(personaOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaOutputDto.getActive(), persona1.getActive());
            assertEquals(personaOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaOutputDto.getImageUrl(), persona1.getImageUrl());
        }

        when(personaServicesMock.getAllPersona(0, 4)).thenReturn(personasOutputDto);
        Iterable<PersonaOutputDto> response = controllersPersona.getAllPersona(0,4, "simple");
        assertEquals(personasOutputDto, response);

        when(personaServicesMock.getAllPersona(0, 4)).thenReturn(personasOutputDto);
        Iterable<PersonaOutputDto> response1 = controllersPersona.getAllPersona(0,4, "full");
        assertEquals(personasOutputDto, response);
    }
    @Test
    void testGetAllPersonaFullEstudiante(){
        Page<Persona> personasPage = new PageImpl<>(personas);
        this.persona.setStudent(this.student);
        this.personaEstudianteOutputDto = this.personaMapper.personaToPersonaEstudianteOutputDto(persona);
        when(personaRepository.findAll(PageRequest.of(0,4))).thenReturn(personasPage);
        Iterable<PersonaEstudianteOutputDto> resultado = personaServices.getAllPersonaFull(0, 4);
        for (PersonaEstudianteOutputDto persona1 : resultado) {
            assertEquals(personaEstudianteOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaEstudianteOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaEstudianteOutputDto.getName(), persona1.getName());
            assertEquals(personaEstudianteOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaEstudianteOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaEstudianteOutputDto.getCity(), persona1.getCity());
            assertEquals(personaEstudianteOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaEstudianteOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaEstudianteOutputDto.getActive(), persona1.getActive());
            assertEquals(personaEstudianteOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaEstudianteOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaEstudianteOutputDto.getImageUrl(), persona1.getImageUrl());
            assertEquals(personaEstudianteOutputDto.getIdStudent(), persona1.getIdStudent());
            assertEquals(personaEstudianteOutputDto.getBranch(), persona1.getBranch());
            assertEquals(personaEstudianteOutputDto.getComments(), persona1.getComments());
            assertEquals(personaEstudianteOutputDto.getNumHoursWeek(), persona1.getNumHoursWeek());
        }
    }
    @Test
    void testGetAllPersonaFullProfesor(){
        Page<Persona> personasPage = new PageImpl<>(personas);
        this.persona.setProfesor(this.profesor);
        this.personaProfesorOutputDto = this.personaMapper.personaToPersonaProfesorOutputDto(persona);
        when(personaRepository.findAll(PageRequest.of(0,4))).thenReturn(personasPage);
        Iterable<PersonaProfesorOutputDto> resultado = personaServices.getPersonaByUsuarioFull("RaquelAlba");
        for (PersonaProfesorOutputDto persona1 : resultado) {
            assertEquals(personaProfesorOutputDto.getIdPersona(), persona1.getIdPersona());
            assertEquals(personaProfesorOutputDto.getUsuario(), persona1.getUsuario());
            assertEquals(personaProfesorOutputDto.getName(), persona1.getName());
            assertEquals(personaProfesorOutputDto.getSurname(), persona1.getSurname());
            assertEquals(personaProfesorOutputDto.getPassword(), persona1.getPassword());
            assertEquals(personaProfesorOutputDto.getCity(), persona1.getCity());
            assertEquals(personaProfesorOutputDto.getCompanyEmail(), persona1.getCompanyEmail());
            assertEquals(personaProfesorOutputDto.getPersonalEmail(), persona1.getPersonalEmail());
            assertEquals(personaProfesorOutputDto.getActive(), persona1.getActive());
            assertEquals(personaProfesorOutputDto.getCreatedDate(), persona1.getCreatedDate());
            assertEquals(personaProfesorOutputDto.getTerminationDate(), persona1.getTerminationDate());
            assertEquals(personaProfesorOutputDto.getImageUrl(), persona1.getImageUrl());
            assertEquals(personaProfesorOutputDto.getIdProfesor(), persona1.getIdProfesor());
            assertEquals(personaProfesorOutputDto.getBranch(), persona1.getBranch());
            assertEquals(personaProfesorOutputDto.getComments(), persona1.getComments());
        }
    }
    @Test
    void testUpdatePersona(){
        PersonaInputDto personaInputUpdated = new PersonaInputDto(1,"RaquelEsp", "1234",
                "Raquel", "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date());
        Persona personaUpdated = new Persona(personaInputUpdated);
        PersonaOutputDto personaOutputUpdated = this.personaMapper.personaToPersonaOutputDto(personaUpdated);
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        PersonaOutputDto resultado =  personaServices.updatePersona(personaInputUpdated, 1);
            assertEquals(personaOutputUpdated.getIdPersona(), resultado.getIdPersona());
            assertEquals(personaOutputUpdated.getUsuario(), resultado.getUsuario());
            assertEquals(personaOutputUpdated.getName(), resultado.getName());
            assertEquals(personaOutputUpdated.getSurname(), resultado.getSurname());
            assertEquals(personaOutputUpdated.getPassword(), resultado.getPassword());
            assertEquals(personaOutputUpdated.getCity(), resultado.getCity());
            assertEquals(personaOutputUpdated.getCompanyEmail(), resultado.getCompanyEmail());
            assertEquals(personaOutputUpdated.getPersonalEmail(), resultado.getPersonalEmail());
            assertEquals(personaOutputUpdated.getActive(), resultado.getActive());
            assertEquals(personaOutputUpdated.getCreatedDate(), resultado.getCreatedDate());
            assertEquals(personaOutputUpdated.getTerminationDate(), resultado.getTerminationDate());
            assertEquals(personaOutputUpdated.getImageUrl(), resultado.getImageUrl());


        when(personaServicesMock.updatePersona(personaInputUpdated, 1)).thenReturn(personaOutputUpdated);
        ResponseEntity<PersonaOutputDto> responseEntity = controllersPersona.updatePersona(personaInputUpdated, 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personaOutputUpdated, responseEntity.getBody());
    }
    @Test
    void testDeletePersona(){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        personaServices.deletePersona(1);
        verify(personaRepository).deleteById(1);

        doNothing().when(personaServicesMock).deletePersona(1);
        ResponseEntity<String> responseEntity = controllersPersona.deletePersona(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se ha borrado a la persona con id: 1", responseEntity.getBody());
    }
    @Test
    void testGetTypeOfPersonaEstudiante(){
        this.persona.setStudent(this.student);
        this.personaEstudianteOutputDto = this.personaMapper.personaToPersonaEstudianteOutputDto(persona);
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        assertEquals("Estudiante", personaServices.getTypeOfPersona(1));
    }
    @Test
    void testGetTypeOfPersonaProfesor(){
        this.persona.setProfesor(this.profesor);
        this.personaProfesorOutputDto = this.personaMapper.personaToPersonaProfesorOutputDto(persona);
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        assertEquals("Profesor", personaServices.getTypeOfPersona(1));
    }
    @Test
    void testGetTypeOfPersonaNone(){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        assertEquals("none", personaServices.getTypeOfPersona(1));
    }
}
