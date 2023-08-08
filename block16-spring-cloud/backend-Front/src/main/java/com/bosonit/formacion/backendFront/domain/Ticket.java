package com.bosonit.formacion.backendFront.domain;

import com.bosonit.formacion.backendFront.controllers.dto.TicketOutputDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ticket")
@Getter
@Setter
public class Ticket {
    @Id
    private String idTicket;
    private String idClient;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;

    public TicketOutputDto ticketToTicketOutputDto () {
        return new TicketOutputDto(
                this.idTicket,
                this.idClient,
                this.clientName,
                this.clientSurname,
                this.clientEmail,
                this.tripOrigin,
                this.tripDestination,
                this.departureDate,
                this.arrivalDate
        );
    }
}
