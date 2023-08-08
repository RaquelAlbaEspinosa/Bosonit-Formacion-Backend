package com.bosonit.formacion.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String idClient;
    private String name;
    private String surname;
    private int years;
    private String email;
    private int phone;
}
