package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.error.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonaGetSetTest {
    public PersonaInputDto personaInputDto;
    @Mock
    public Persona persona;
    @BeforeEach
    public void runBefore () {
        this.personaInputDto = new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date());
        this.persona = new Persona(this.personaInputDto);
    }
    @Test
    void testGetters() {
        assertEquals(1,  persona.getIdPersona());
        assertEquals("RaquelAlba",  persona.getUsuario());
        assertEquals("1234",  persona.getPassword());
        assertEquals("Raquel",  persona.getName());
        assertEquals("Alba",  persona.getSurname());
        assertEquals("raquel@bosonit.com",  persona.getCompanyEmail());
        assertEquals("raquel@raquel.com",  persona.getPersonalEmail());
        assertEquals("Málaga",  persona.getCity());
        assertEquals(true,  persona.getActive());
        assertEquals(new Date().getDate(),  persona.getCreatedDate().getDate());
        assertEquals("urlImage",  persona.getImageUrl());
        assertEquals(new Date().getDate(),  persona.getTerminationDate().getDate());
    }
    @Test
    void testSetters() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.persona.setIdPersona(2);
        assertEquals(2,  persona.getIdPersona());
        this.persona.setUsuario("Ralbae");
        assertEquals("Ralbae",  persona.getUsuario());
        this.persona.setPassword("5678");
        assertEquals("5678",  persona.getPassword());
        this.persona.setName("Rachel");
        assertEquals("Rachel",  persona.getName());
        this.persona.setSurname("Espinosa");
        assertEquals("Espinosa",  persona.getSurname());
        this.persona.setCompanyEmail("rachel@bosonit.com");
        assertEquals("rachel@bosonit.com",  persona.getCompanyEmail());
        this.persona.setPersonalEmail("alba@alba.com");
        assertEquals("alba@alba.com",  persona.getPersonalEmail());
        this.persona.setCity("Madrid");
        assertEquals("Madrid",  persona.getCity());
        this.persona.setActive(false);
        assertEquals(false,  persona.getActive());
        this.persona.setImageUrl("ImageUrl");
        assertEquals("ImageUrl", persona.getImageUrl());
        try {
            this.persona.setCreatedDate(sdf.parse("2021-01-01"));
            assertEquals(sdf.parse("2021-01-01").getDate(), persona.getCreatedDate().getDate());
            this.persona.setTerminationDate(sdf.parse("2023-01-01"));
            assertEquals(sdf.parse("2021-01-01").getDate(), persona.getTerminationDate().getDate());
        } catch (ParseException e) {
            throw new ParseException("las fechas no están bien", 1);
        }
    }
    @Test
    void testConstructorPersonaExceptionsUser () {
        //Usuario
        try {
            this.personaInputDto.setUsuario(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            System.out.println(ex.getMessage());
            assertEquals("Usuario no puede ser nulo", ex.getMessage());
        }
        try {
            this.personaInputDto.setUsuario("usuariodemasiadolargo");
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Longitud de usuario no puede ser superior a 10 caracteres", ex.getMessage());
        }
        try {
            this.personaInputDto.setUsuario("corto");
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Longitud de usuario no puede ser menor a 6 caracteres", ex.getMessage());
        }
    }
    @Test
    void testConstructorPersonaExceptionsPassword () {
        //Contraseña
        try {
            this.personaInputDto.setPassword(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Contraseña no puede ser nulo", ex.getMessage());
        }
    }
    @Test
    void testConstructorPersonaExceptionsName () {
        //Nombre
        try {
            this.personaInputDto.setName(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Nombre no puede ser nulo", ex.getMessage());
        }
    }
    @Test
    void testConstructorPersonaExceptionsCompanyEmail () {
        //Email de compañía
        try {
            this.personaInputDto.setCompanyEmail(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Email de compañía no puede ser nulo", ex.getMessage());
        }
    }
    @Test
    void testConstructorPersonaExceptionsPersonalEmail () {
        //Email personal
        try {
            this.personaInputDto.setPersonalEmail(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Email personal no puede ser nulo", ex.getMessage());
        }
    }
    @Test
    void testConstructorPersonaExceptionsCity () {
        //Ciudad
        try {
            this.personaInputDto.setCity(null);
            Persona persona = new Persona(this.personaInputDto);
            fail("Se esperaba UnprocessableEntityException, pero no se lanzó ninguna excepción.");
        } catch (UnprocessableEntityException ex) {
            assertEquals("Ciudad no puede ser null", ex.getMessage());
        }
    }
}
