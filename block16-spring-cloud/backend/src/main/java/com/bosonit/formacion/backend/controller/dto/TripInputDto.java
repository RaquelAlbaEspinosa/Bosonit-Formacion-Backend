package com.bosonit.formacion.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripInputDto {
    private String idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean status = true;
}
