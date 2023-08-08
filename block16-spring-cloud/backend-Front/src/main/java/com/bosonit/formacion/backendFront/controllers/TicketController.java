package com.bosonit.formacion.backendFront.controllers;

import com.bosonit.formacion.backendFront.application.TicketService;
import com.bosonit.formacion.backendFront.controllers.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/generateTicket/{idClient}/{idTrip}")
    public TicketOutputDto generateTicket (@PathVariable String idClient,
                                           @PathVariable String idTrip){
        return ticketService.generateTicket(idClient, idTrip);
    }
}
