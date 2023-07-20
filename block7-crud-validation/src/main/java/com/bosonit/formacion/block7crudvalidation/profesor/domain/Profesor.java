package com.bosonit.formacion.block7crudvalidation.profesor.domain;

import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "profesor")
@Getter
@Setter
public class Profesor {
    @Id
    @Column(name = "id_profesor")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String idProfesor;
    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;
    @OneToMany
    private List<Student> students;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama")
    private String branch;
}
