package com.bosonit.formacion.backendFront.application;

import com.bosonit.formacion.backendFront.controllers.dto.ClientOutputDto;
import com.bosonit.formacion.backendFront.controllers.dto.TicketOutputDto;
import com.bosonit.formacion.backendFront.controllers.dto.TripOutputDto;
import com.bosonit.formacion.backendFront.domain.Ticket;
import com.bosonit.formacion.backendFront.feign.TripClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TripClientFeign tripClientFeign;
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public TicketOutputDto generateTicket(String idClient, String idTrip) {
        ClientOutputDto client = tripClientFeign.getClientById(idClient);
        TripOutputDto trip = tripClientFeign.getTripById(idTrip);
        Ticket ticket = new Ticket();
        ticket.setIdClient(client.getIdClient());
        ticket.setClientName(client.getName());
        ticket.setClientSurname(client.getSurname());
        ticket.setClientEmail(client.getEmail());
        ticket.setTripOrigin(trip.getOrigin());
        ticket.setTripDestination(trip.getDestination());
        ticket.setDepartureDate(trip.getDepartureDate());
        ticket.setArrivalDate(trip.getArrivalDate());
        mongoTemplate.save(ticket);
        return ticket.ticketToTicketOutputDto();
    }
}
