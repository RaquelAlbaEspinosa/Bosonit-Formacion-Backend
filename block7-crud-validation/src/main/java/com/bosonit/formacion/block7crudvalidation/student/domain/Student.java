package com.bosonit.formacion.block7crudvalidation.student.domain;

import com.bosonit.formacion.block7crudvalidation.asignatura.domain.Asignatura;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    Persona persona;
    @Column(name = "horas_por_semana")
    int numHoursWeek;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "rama")
    String branch;
    @ManyToOne
    @JoinColumn(name = "idProfesor")
    @JsonIgnore
    Profesor profesor;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Column(name = "asignaturas")
    @JsonIgnore
    List<Asignatura> alumnosEstudios;
    public Student (StudentInputDto studentInputDto){
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
    }
    public StudentOutputDto studentToStudentOutputDto () {
        return new StudentOutputDto(
                this.idStudent,
                this.numHoursWeek,
                this.comments,
                this.branch,
                this.profesor != null ? this.profesor.getIdProfesor() : null
        );
    }
    public StudentFullOutputDto studentToStudentFullOutputDto(){
        return new StudentFullOutputDto(
                this.idStudent,
                this.numHoursWeek,
                this.comments,
                this.branch,
                this.persona.getIdPersona(),
                this.persona.getUsuario(),
                this.persona.getPassword(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompanyEmail(),
                this.persona.getPersonalEmail(),
                this.persona.getCity(),
                this.persona.getActive(),
                this.persona.getCreatedDate(),
                this.persona.getImageUrl(),
                this.persona.getTerminationDate(),
                this.profesor != null ? this.profesor.getIdProfesor() : null
        );
    }
}
