package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentGetSetTest {
    @Mock
    public Student student;
    @BeforeEach
    public void runBefore () {
        StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
        this.student = studentMapper.studentInputDtoToStudent(new StudentInputDto("1234",
                40, "fantástico", "back", 1, "1234"));
    }
    @Test
    void testGetters () {
        assertEquals("1234", student.getIdStudent());
        assertEquals(40, student.getNumHoursWeek());
        assertEquals("fantástico", student.getComments());
        assertEquals("back", student.getBranch());
    }
    @Test
    void testSetters () {
        this.student.setIdStudent("5678");
        assertEquals("5678", student.getIdStudent());
        this.student.setNumHoursWeek(35);
        assertEquals(35, student.getNumHoursWeek());
        this.student.setComments("genial");
        assertEquals("genial", student.getComments());
        this.student.setBranch("front");
        assertEquals("front", student.getBranch());
    }
}
