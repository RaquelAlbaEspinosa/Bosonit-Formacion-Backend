package com.bosonit.formacion.backend.application;

import com.bosonit.formacion.backend.controller.dto.TripInputDto;
import com.bosonit.formacion.backend.controller.dto.TripOutputDto;

public interface TripService {
    TripOutputDto addTrip (TripInputDto tripInputDto);
    TripOutputDto getTripById (String id);
    Iterable<TripOutputDto> getAllTrip (int pageNumber, int pageSize);
    TripOutputDto updateTrip (String id, TripInputDto tripInputDto);
    void deleteTripById (String id);
    TripOutputDto addPassengerToTrip (String idTrip, String idClient);
    Integer countPassengersOnATrip (String idTrip);
    TripOutputDto changeStatus (String id);
    String getStatus (String id);
}
