package com.bosonit.formacion.block7crudvalidation.profesor.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfesorFullOutputDto extends ProfesorOutputDto {
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
