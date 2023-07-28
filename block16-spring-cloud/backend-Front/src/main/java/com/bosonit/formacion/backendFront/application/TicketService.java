package com.bosonit.formacion.backendFront.application;

import com.bosonit.formacion.backendFront.controllers.dto.TicketOutputDto;

public interface TicketService {
    TicketOutputDto generateTicket (String idClient, String idTicket);
}
