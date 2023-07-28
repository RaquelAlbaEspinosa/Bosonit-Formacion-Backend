package com.bosonit.formacion.backend.controller.mapper;

import com.bosonit.formacion.backend.controller.dto.TripInputDto;
import com.bosonit.formacion.backend.controller.dto.TripOutputDto;
import com.bosonit.formacion.backend.domain.Trip;
import org.mapstruct.Mapper;

@Mapper
public interface TripMapper {
    Trip tripInputDtoToTrip (TripInputDto tripInputDto);
    TripOutputDto tripToTripOutputDto (Trip trip);
}
