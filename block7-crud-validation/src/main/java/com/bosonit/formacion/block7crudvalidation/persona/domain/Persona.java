package com.bosonit.formacion.block7crudvalidation.persona.domain;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.error.UnprocessableEntityException;
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
    int idPersona;
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Student student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
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
        //Sin validación
        this.surname = personaInputDto.getSurname();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
        this.idPersona = personaInputDto.getId();
    }
}
