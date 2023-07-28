package com.bosonit.formacion.backendFront.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private String idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
}
