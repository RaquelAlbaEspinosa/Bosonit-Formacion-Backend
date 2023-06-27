package com.bosonit.formacion.block7crudvalidation.profesor.domain;

import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    List<Student> students;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "rama")
    String branch;

    public Profesor(ProfesorInputDto profesorInputDto){
        this.idProfesor = profesorInputDto.getIdProfesor();
        this.comments = profesorInputDto.getComments();
        this.branch = profesorInputDto.getBranch();
    }

    public ProfesorOutputDto profesorToProfesorOutputDto () {
        return new ProfesorOutputDto(
                this.idProfesor,
                this.comments,
                this.branch
        );
    }
    public ProfesorFullOutputDto profesorToProfesorFullOutputDto () {
        return new ProfesorFullOutputDto(
                this.idProfesor,
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
                this.persona.getTerminationDate()
        );
    }
}
