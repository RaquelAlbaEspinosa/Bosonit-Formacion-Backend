package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.persona.repository.PersonaRepository;
import com.bosonit.formacion.block7crudvalidation.profesor.application.ProfesorService;
import com.bosonit.formacion.block7crudvalidation.profesor.application.ProfesorServicelmpl;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.ControllersProfesor;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper.ProfesorMapper;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.profesor.repository.ProfesorRepository;
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

class ProfesorServiceControllerMockTest {
    @Mock
    public PersonaRepository personaRepository;
    public Persona persona;
    public Profesor profesor;
    public ProfesorInputDto profesorInputDto;
    public ProfesorOutputDto profesorOutputDto;
    public ProfesorFullOutputDto profesorFullOutputDto;
    public ProfesorMapper profesorMapper;
    public Iterable<ProfesorOutputDto> profesoresOutputDto;
    public List<Profesor> profesores;
    @Mock
    public ProfesorService profesorServiceMock;
    @Mock
    public ProfesorRepository profesorRepository;
    @InjectMocks
    public ControllersProfesor controllersProfesor;
    @InjectMocks
    public ProfesorServicelmpl profesorService;
    @BeforeEach
    public void runBefore(){
        //se recargan los Mocks de Mockito
        MockitoAnnotations.initMocks(this);
        //damos valor a persona y la guardamos
        this.persona = new Persona(new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date()));
        this.personaRepository.save(this.persona);
        //damos valor a profesor y lo guardamos
        this.profesorInputDto = new ProfesorInputDto("1234","bien, todo bien", "back", 1);
        this.profesorMapper = Mappers.getMapper(ProfesorMapper.class);
        this.profesor = this.profesorMapper.profesorInputDtoProfesor(this.profesorInputDto);
        this.profesorOutputDto = this.profesorMapper.profesorToProfesorOutputDto(this.profesor);
        this.profesorRepository.save(this.profesor);
        //añadimos un profesorOutput al Iterable
        List<ProfesorOutputDto> profesorOutputDto1 = new ArrayList<>();
        profesorOutputDto1.add(profesorOutputDto);
        //añadimos un profesor a la lista
        this.profesoresOutputDto = profesorOutputDto1;
        this.profesores = new ArrayList<>();
        this.profesores.add(profesor);
    }
    @Test
    void testAddProfesor(){
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        ProfesorOutputDto result = profesorService.addProfesor(profesorInputDto);
        assertEquals(profesorOutputDto.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorOutputDto.getComments(), result.getComments());
        assertEquals(profesorOutputDto.getBranch(), result.getBranch());

        when(profesorServiceMock.addProfesor(profesorInputDto)).thenReturn(profesorOutputDto);
        ResponseEntity<ProfesorOutputDto> responseEntity = controllersProfesor.addProfesor(profesorInputDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(profesorOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetProfesorById (){
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        ProfesorOutputDto result = profesorService.getProfesorById("1234");
        assertEquals(profesorOutputDto.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorOutputDto.getComments(), result.getComments());
        assertEquals(profesorOutputDto.getBranch(), result.getBranch());

        when(profesorServiceMock.getProfesorById("1234")).thenReturn(profesorOutputDto);
        ResponseEntity<ProfesorOutputDto> responseEntity = controllersProfesor.getProfesorById("1234", "simple");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profesorOutputDto, responseEntity.getBody());
    }
    @Test
    void testGetProfesorByIdFull (){
        this.profesorFullOutputDto = this.profesorMapper.profesorToProfesorFullOutputDto(profesor);
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        ProfesorFullOutputDto result = profesorService.getProfesorByIdFull("1234");
        assertEquals(profesorFullOutputDto.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorFullOutputDto.getComments(), result.getComments());
        assertEquals(profesorFullOutputDto.getBranch(), result.getBranch());
        assertEquals(profesorFullOutputDto.getIdPersona(), result.getIdPersona());
        assertEquals(profesorFullOutputDto.getUsuario(), result.getUsuario());
        assertEquals(profesorFullOutputDto.getPassword(), result.getPassword());
        assertEquals(profesorFullOutputDto.getName(), result.getName());
        assertEquals(profesorFullOutputDto.getSurname(), result.getSurname());
        assertEquals(profesorFullOutputDto.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(profesorFullOutputDto.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(profesorFullOutputDto.getCity(), result.getCity());
        assertEquals(profesorFullOutputDto.getActive(), result.getActive());
        assertEquals(profesorFullOutputDto.getCreatedDate(), result.getCreatedDate());
        assertEquals(profesorFullOutputDto.getImageUrl(), result.getImageUrl());
        assertEquals(profesorFullOutputDto.getTerminationDate(), result.getTerminationDate());

        when(profesorServiceMock.getProfesorByIdFull("1234")).thenReturn(profesorFullOutputDto);
        ResponseEntity<ProfesorOutputDto> responseEntity2 = controllersProfesor.getProfesorById("1234", "full");
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals(profesorFullOutputDto, responseEntity2.getBody());
    }
    @Test
    void testGetProfesorByUsuario(){
        when(profesorRepository.findAll().stream()
                .filter(persona1 -> persona.getUsuario().equals("RaquelAlba")).toList())
                .thenReturn(profesores);
        Iterable<ProfesorOutputDto> result = profesorService.getProfesorByUsuario("RaquelAlba");
        for(ProfesorOutputDto profesor : result){
            assertEquals(profesorOutputDto.getIdProfesor(), profesor.getIdProfesor());
            assertEquals(profesorOutputDto.getComments(), profesor.getComments());
            assertEquals(profesorOutputDto.getBranch(), profesor.getBranch());
        }

        when(profesorServiceMock.getProfesorByUsuario("RaquelAlba")).thenReturn(profesoresOutputDto);
        Iterable<ProfesorOutputDto> response = controllersProfesor.getProfesorByUsuario("RaquelAlba");
        assertEquals(profesoresOutputDto, response);
    }
    @Test
    void testGetAllProfesor(){
        Page<Profesor> profesorPage = new PageImpl<>(profesores);
        when(profesorRepository.findAll(PageRequest.of(0,4))).thenReturn(profesorPage);
        Iterable<ProfesorOutputDto> result = profesorService.getAllProfesor(0,4);
        for(ProfesorOutputDto profesor : result){
            assertEquals(profesorOutputDto.getIdProfesor(), profesor.getIdProfesor());
            assertEquals(profesorOutputDto.getComments(), profesor.getComments());
            assertEquals(profesorOutputDto.getBranch(), profesor.getBranch());
        }

        when(profesorServiceMock.getAllProfesor(0, 4)).thenReturn(profesoresOutputDto);
        Iterable<ProfesorOutputDto> response = controllersProfesor.getAllProfesor(0, 4);
        assertEquals(profesoresOutputDto, response);
    }
    @Test
    void testUpdateProfesor(){
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        profesorRepository.findById("1234").orElseThrow()
                .setPersona(personaRepository.findById(1).orElseThrow());
        ProfesorOutputDto result = profesorService.updateProfesor(profesorInputDto, "1234");
        assertEquals(profesorOutputDto.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorOutputDto.getComments(), result.getComments());
        assertEquals(profesorOutputDto.getBranch(), result.getBranch());

        when(profesorServiceMock.updateProfesor(profesorInputDto,"1234")).thenReturn(profesorOutputDto);
        ResponseEntity<ProfesorOutputDto> responseEntity = controllersProfesor.updateProfesor(profesorInputDto, "1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profesorOutputDto, responseEntity.getBody());
    }
    @Test
    void testDeleteProfesor(){
        when(profesorRepository.findById("1234")).thenReturn(Optional.ofNullable(profesor));
        profesorService.deleteProfesor("1234");
        verify(profesorRepository).delete(profesor);

        doNothing().when(profesorServiceMock).deleteProfesor("1234");
        ResponseEntity<String> responseEntity = controllersProfesor.deleteProfesor("1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se ha borrado el profesor con id: 1234", responseEntity.getBody());
    }
}
