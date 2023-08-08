package com.bosonit.formacion.backendFront.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String idClient;
    private String name;
    private String surname;
    private int years;
    private String email;
    private int phone;
}
