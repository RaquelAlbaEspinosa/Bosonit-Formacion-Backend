package com.bosonit.formacion.block7crudvalidation.asignatura.domain;

import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asignatura")
@Getter
@Setter
public class Asignatura {
    @Id
    @Column(name = "id_study")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String idStudy;

    @ManyToMany
    @JoinColumn(name = "idStudent")
    private List<Student> student;
    @Column(name = "asignatura")
    private String nombreAsignatura;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "fecha_inicio")
    private Date initialDate;
    @Column(name = "fecha_final")
    private Date finishDate;
}
