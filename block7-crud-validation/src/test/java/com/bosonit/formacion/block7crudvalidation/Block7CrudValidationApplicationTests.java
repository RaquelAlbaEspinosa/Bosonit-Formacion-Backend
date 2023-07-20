package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.feign.ProfesorFeign;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class Block7CrudValidationApplicationTests {
    @MockBean
    private ProfesorFeign profesorFeign;
    @Test
    public void contextLoads() {
        ProfesorOutputDto mockOutput = new ProfesorOutputDto();
        ResponseEntity<ProfesorOutputDto> mockResponse = ResponseEntity.ok(mockOutput);
        when(profesorFeign.getProfesorById(anyString(), anyString())).thenReturn(mockResponse);
    }
}
