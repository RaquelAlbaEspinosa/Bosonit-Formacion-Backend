package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.mapper.AsignaturaMapper;
import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsignaturaGetSetTest {
    @Mock
    public Asignatura asignatura;
    @BeforeEach
    public void runBefore(){
        AsignaturaMapper asignaturaMapper = Mappers.getMapper(AsignaturaMapper.class);
        this.asignatura = asignaturaMapper.asignaturaInputDtoToAsignatura(new AsignaturaInputDto("1234",
                null, "Java", "Interesante", new Date(), new Date()));
    }
    @Test
    void testGetters () {
        assertEquals("1234",  asignatura.getIdStudy());
        assertEquals("Java",  asignatura.getNombreAsignatura());
        assertEquals("Interesante",  asignatura.getComments());
        assertEquals(new Date().getDate(),  asignatura.getInitialDate().getDate());
        assertEquals(new Date().getDate(),  asignatura.getFinishDate().getDate());
    }
    @Test
    void testSetters () throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.asignatura.setIdStudy("5678");
        assertEquals("5678",  asignatura.getIdStudy());
        this.asignatura.setNombreAsignatura("Spring Boot");
        assertEquals("Spring Boot",  asignatura.getNombreAsignatura());
        this.asignatura.setComments("Muy interesante");
        assertEquals("Muy interesante",  asignatura.getComments());
        try {
            this.asignatura.setInitialDate(sdf.parse("2021-01-01"));
            assertEquals(sdf.parse("2021-01-01").getDate(), asignatura.getInitialDate().getDate());
            this.asignatura.setFinishDate(sdf.parse("2023-01-01"));
            assertEquals(sdf.parse("2023-01-01").getDate(), asignatura.getFinishDate().getDate());
        } catch (ParseException e){
            throw new ParseException("las fechas están mal", 1);
        }
    }
}
