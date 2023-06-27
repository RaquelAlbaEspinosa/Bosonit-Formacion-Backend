package com.bosonit.formacion.block7crudvalidation.asignatura.domain;

import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "alumnos_estudios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @Column(name = "id_study")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String idStudy;

    @ManyToOne
    @JoinColumn(name = "idStudent")
    @JsonIgnore
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "fecha_inicio")
    Date initialDate;
    @Column(name = "fecha_final")
    Date finishDate;

    public Asignatura (AsignaturaInputDto asignaturaInputDto){
        this.idStudy = asignaturaInputDto.getIdStudy();
        this.asignatura = asignaturaInputDto.getAsignatura();
        this.comments = asignaturaInputDto.getComments();
        this.initialDate = asignaturaInputDto.getInitialDate();
        this.finishDate = asignaturaInputDto.getFinishDate();
    }
    public AsignaturaOutputDto asignaturaToAsignaturaOutputDto () {
        return new AsignaturaOutputDto(
                this.idStudy,
                this.asignatura,
                this.comments,
                this.initialDate,
                this.finishDate
        );
    }
}
