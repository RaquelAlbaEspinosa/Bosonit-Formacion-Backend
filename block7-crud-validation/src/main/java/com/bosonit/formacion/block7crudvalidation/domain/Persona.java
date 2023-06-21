package com.bosonit.formacion.block7crudvalidation.domain;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int id;
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

    public Persona (PersonaInputDto personaInputDto) throws Exception {
        //Usuario
        if (personaInputDto.getUsuario() == null) {
            throw new Exception("Usuario no puede ser nulo");
        } else if (personaInputDto.getUsuario().length()>10) {
            throw  new Exception("Longitud de usuario no puede ser superior a 10 caracteres");
        } else if (personaInputDto.getUsuario().length()<6) {
            throw  new Exception("Longitud de usuario no puede ser menor a 6 caracteres");
        } else {
            this.usuario = personaInputDto.getUsuario();
        }
        //Contraseña
        if (personaInputDto.getPassword() == null) {
            throw new Exception("Contraseña no puede ser nulo");
        } else {
            this.password = personaInputDto.getPassword();
        }
        //Nombre
        if(personaInputDto.getName() == null){
            throw new Exception("Nombre no puede ser nulo");
        } else {
            this.name = personaInputDto.getName();
        }
        //Email de compañía
        if(personaInputDto.getCompanyEmail() == null){
            throw new Exception("Email de compañía no puede ser nulo");
        } else {
            this.companyEmail = personaInputDto.getCompanyEmail();
        }
        //Email personal
        if(personaInputDto.getPersonalEmail() == null){
            throw new Exception("Email personal no puede ser nulo");
        } else {
            this.personalEmail = personaInputDto.getPersonalEmail();
        }
        //Ciudad
        if(personaInputDto.getCity() == null){
            throw new Exception("Ciudad no puede ser null");
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
    }
    public PersonaOutputDto personaToPersonaOutputDto () {
        return new PersonaOutputDto(
                this.id,
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
}
