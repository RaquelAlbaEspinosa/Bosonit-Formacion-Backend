package com.bosonit.formacion.block13mongodb.domain;

import com.bosonit.formacion.block13mongodb.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13mongodb.controller.dto.PersonaOutputDto;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    String idPersona;
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
    public Persona (PersonaInputDto personaInputDto){
        this.idPersona = personaInputDto.getIdPersona();
        this.usuario = personaInputDto.getUsuario();
        this.password = personaInputDto.getPassword();
        this.name = personaInputDto.getName();
        this.surname = personaInputDto.getSurname();
        this.companyEmail = personaInputDto.getCompanyEmail();
        this.personalEmail = personaInputDto.getPersonalEmail();
        this.city = personaInputDto.getCity();
        this.active = personaInputDto.getActive();
        this.createdDate = personaInputDto.getCreatedDate();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
    }
    public PersonaOutputDto personaToPersonaOutputDto (){
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
}
