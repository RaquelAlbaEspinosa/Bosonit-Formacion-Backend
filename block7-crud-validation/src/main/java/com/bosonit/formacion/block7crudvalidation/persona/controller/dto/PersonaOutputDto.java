package com.bosonit.formacion.block7crudvalidation.persona.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class PersonaOutputDto {
    private int idPersona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;
}
