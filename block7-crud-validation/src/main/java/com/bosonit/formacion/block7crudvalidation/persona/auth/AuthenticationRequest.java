package com.bosonit.formacion.block7crudvalidation.persona.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String usuario;
    private String password;
}
