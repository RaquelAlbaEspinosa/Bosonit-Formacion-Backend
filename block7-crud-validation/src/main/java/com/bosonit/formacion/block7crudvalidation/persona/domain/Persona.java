package com.bosonit.formacion.block7crudvalidation.persona.domain;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaEstudianteOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.error.UnprocessableEntityException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int idPersona;
    String usuario;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imageUrl;
    Date terminationDate;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    Student student;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    Profesor profesor;

    public Persona (PersonaInputDto personaInputDto) throws UnprocessableEntityException {
        //Usuario
        if (personaInputDto.getUsuario() == null) {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        } else if (personaInputDto.getUsuario().length()>10) {
            throw  new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        } else if (personaInputDto.getUsuario().length()<6) {
            throw  new UnprocessableEntityException("Longitud de usuario no puede ser menor a 6 caracteres");
        } else {
            this.usuario = personaInputDto.getUsuario();
        }
        //Contraseña
        if (personaInputDto.getPassword() == null) {
            throw new UnprocessableEntityException("Contraseña no puede ser nulo");
        } else {
            this.password = personaInputDto.getPassword();
        }
        //Nombre
        if(personaInputDto.getName() == null){
            throw new UnprocessableEntityException("Nombre no puede ser nulo");
        } else {
            this.name = personaInputDto.getName();
        }
        //Email de compañía
        if(personaInputDto.getCompanyEmail() == null){
            throw new UnprocessableEntityException("Email de compañía no puede ser nulo");
        } else {
            this.companyEmail = personaInputDto.getCompanyEmail();
        }
        //Email personal
        if(personaInputDto.getPersonalEmail() == null){
            throw new UnprocessableEntityException("Email personal no puede ser nulo");
        } else {
            this.personalEmail = personaInputDto.getPersonalEmail();
        }
        //Ciudad
        if(personaInputDto.getCity() == null){
            throw new UnprocessableEntityException("Ciudad no puede ser null");
        } else {
            this.city = personaInputDto.getCity();
        }
        //Active
        if(personaInputDto.getActive() == null){
            this.active = false;
        } else {
            this.active = personaInputDto.getActive();
        }
        //Fecha de creación
        if(personaInputDto.getCreatedDate() == null){
            this.createdDate = new Date();
        } else {
            this.createdDate = personaInputDto.getCreatedDate();
        }
        //Los que pueden ser null
        this.surname = personaInputDto.getSurname();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
        this.idPersona = personaInputDto.getId();
    }
    public PersonaOutputDto personaToPersonaOutputDto () {
        return new PersonaOutputDto(
                this.idPersona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate
        );
    }
    public PersonaEstudianteOutputDto personaToPersonaEstudianteOutputDto () {
        return new PersonaEstudianteOutputDto(
                this.idPersona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate,
                this.student.getIdStudent(),
                this.student.getNumHoursWeek(),
                this.student.getComments(),
                this.student.getBranch()
        );
    }
    public PersonaProfesorOutputDto personaToPersonaProfesorOutputDto () {
        return new PersonaProfesorOutputDto(
                this.idPersona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate,
                this.profesor.getIdProfesor(),
                this.profesor.getComments(),
                this.profesor.getBranch()
        );
    }
}
