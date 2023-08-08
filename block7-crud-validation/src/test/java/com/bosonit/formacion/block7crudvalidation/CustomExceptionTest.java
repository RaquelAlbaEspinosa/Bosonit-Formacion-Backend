package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.error.CustomError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionTest {
    @Mock
    CustomError customError;
    @BeforeEach
    public void runBefore () {
        MockitoAnnotations.initMocks(this);
        this.customError = new CustomError(new Date(), 200, "Todo correcto");
    }
    @Test
    void testGetters () {
        assertEquals(new Date().getDate(), customError.getTimestamp().getDate());
        assertEquals(200, customError.getHttpCodeNumber());
        assertEquals("Todo correcto", customError.getMessage());
    }
    @Test
    void testSetters () throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.customError.setTimestamp(sdf.parse("2022-01-01"));
            assertEquals(sdf.parse("2022-01-01").getDate(), customError.getTimestamp().getDate());
        } catch (ParseException e) {
            throw new ParseException("La fecha está mal", 1);
        }
        this.customError.setHttpCodeNumber(201);
        assertEquals(201, customError.getHttpCodeNumber());
        this.customError.setMessage("Creado");
        assertEquals("Creado", customError.getMessage());
    }
}
