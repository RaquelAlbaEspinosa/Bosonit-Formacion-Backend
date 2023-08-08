package com.bosonit.formacion.backend.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    private String idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private List<String> passenger = new ArrayList<>();
    private boolean status;
}
