package com.bosonit.formacion.block7crudvalidation.student.domain;

import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "id_student")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String idStudent;
    @OneToOne
    @JoinColumn(name = "idPersona")
    Persona persona;
    @Column(name = "horas_por_semana")
    int numHoursWeek;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "rama")
    String branch;
    @ManyToOne
    @JoinColumn(name = "idProfesor")
    Profesor profesor;
    @ManyToMany(mappedBy = "student")
    @Column(name = "asignaturas")
    List<Asignatura> alumnosEstudios;
}
