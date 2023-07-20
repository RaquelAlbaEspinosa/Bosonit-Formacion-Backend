package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.mapper.ProfesorMapper;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfesorGetSetTest {
    public Persona persona;
    @Mock
    public Profesor profesor;
    @BeforeEach
    public void runBefore () {
        ProfesorMapper profesorMapper = Mappers.getMapper(ProfesorMapper.class);
        this.profesor = profesorMapper.profesorInputDtoProfesor(new ProfesorInputDto("1234",
                "bien, todo bien", "back", 1));
        this.persona = new Persona(new PersonaInputDto(1,"RaquelAlba", "1234", "Raquel",
                "Alba", "raquel@bosonit.com", "raquel@raquel.com",
                "Málaga", true, new Date(), "urlImage", new Date()));
        this.profesor.setPersona(persona);
    }
    @Test
    void testGetters () {
        assertEquals("1234", profesor.getIdProfesor());
        assertEquals("bien, todo bien", profesor.getComments());
        assertEquals("back", profesor.getBranch());
        assertEquals(this.persona, profesor.getPersona());
    }
    @Test
    void testSetters () {
        this.profesor.setIdProfesor("5678");
        assertEquals("5678", profesor.getIdProfesor());
        this.profesor.setBranch("front");
        assertEquals("front", profesor.getBranch());
        this.profesor.setComments("muy bien");
        assertEquals("muy bien", profesor.getComments());
    }
}
