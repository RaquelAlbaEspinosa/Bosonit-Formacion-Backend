package com.bosonit.formacion.backendFront.controllers.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TicketOutputDto {
    private String idTicket;
    private String idClient;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;
}
