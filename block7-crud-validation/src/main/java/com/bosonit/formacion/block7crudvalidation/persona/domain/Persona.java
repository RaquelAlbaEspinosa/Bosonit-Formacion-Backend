package com.bosonit.formacion.block7crudvalidation.persona.domain;

import com.bosonit.formacion.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.profesor.domain.Profesor;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import com.bosonit.formacion.block7crudvalidation.error.UnprocessableEntityException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
public class Persona implements UserDetails {
    @Id
    @GeneratedValue
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Profesor profesor;
    private boolean admin;
    @Enumerated(EnumType.STRING)
    private Role role;

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
        this.admin = personaInputDto.isAdmin();
        if(personaInputDto.isAdmin()){
            this.role = Role.ADMIN;
        } else {
            this.role = Role.USER;
        }
        //Sin validación
        this.surname = personaInputDto.getSurname();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
        this.idPersona = personaInputDto.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }
    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
