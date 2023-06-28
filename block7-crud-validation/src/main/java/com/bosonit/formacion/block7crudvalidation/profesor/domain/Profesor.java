package com.bosonit.formacion.block7crudvalidation.profesor.domain;

import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @Column(name = "id_profesor")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String idProfesor;
    @OneToOne
    @JoinColumn(name = "idPersona")
    Persona persona;
    @OneToMany
    List<Student> students;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "rama")
    String branch;
}
