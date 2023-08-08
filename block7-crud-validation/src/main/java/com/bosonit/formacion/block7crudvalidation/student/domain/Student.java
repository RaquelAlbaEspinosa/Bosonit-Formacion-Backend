package com.bosonit.formacion.block7crudvalidation.student.domain;

import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "id_student")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String idStudent;
    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;
    @Column(name = "horas_por_semana")
    private int numHoursWeek;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama")
    private String branch;
    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;
    @ManyToMany(mappedBy = "student")
    @Column(name = "asignaturas")
    private List<Asignatura> alumnosEstudios;
}
