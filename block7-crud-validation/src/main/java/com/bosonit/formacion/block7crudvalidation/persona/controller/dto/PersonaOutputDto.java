package com.bosonit.formacion.block7crudvalidation.persona.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDto {
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
}
