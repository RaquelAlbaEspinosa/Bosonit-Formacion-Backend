package com.bosonit.formacion.backend.application;

import com.bosonit.formacion.backend.controller.dto.TripInputDto;
import com.bosonit.formacion.backend.controller.dto.TripOutputDto;
import com.bosonit.formacion.backend.controller.mapper.TripMapper;
import com.bosonit.formacion.backend.domain.Client;
import com.bosonit.formacion.backend.domain.Trip;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
        Trip trip = tripMapper.tripInputDtoToTrip(tripInputDto);
        return tripMapper.tripToTripOutputDto(mongoTemplate.save(trip));
    }

    @Override
    public TripOutputDto getTripById(String id) {
        TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
        return tripMapper.tripToTripOutputDto(mongoTemplate.findById(id, Trip.class));
    }

    @Override
    public Iterable<TripOutputDto> getAllTrip(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Query query = new Query();
        query.with(pageRequest);
        return mongoTemplate.find(query, Trip.class)
                .stream().map(trip -> {
                    TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
                    return tripMapper.tripToTripOutputDto(trip);
                }).toList();
    }

    @Override
    public TripOutputDto updateTrip(String id, TripInputDto tripInputDto) {
        TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
        Trip trip = tripMapper.tripInputDtoToTrip(tripInputDto);
        trip.setIdTrip(id);
        mongoTemplate.save(trip);
        return tripMapper.tripToTripOutputDto(trip);
    }

    @Override
    public void deleteTripById(String id) {
        Query query = new Query(Criteria.where("idTrip").is(id));
        mongoTemplate.remove(query, Trip.class);
    }

    @Override
    public TripOutputDto addPassengerToTrip(String idTrip, String idClient) {
        TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
        Trip trip = mongoTemplate.findById(idTrip, Trip.class);
        if(mongoTemplate.findById(idClient, Client.class) != null) {
            if(!trip.getPassenger().contains(idClient)) {
                trip.getPassenger().add(idClient);
            }
        }
        return tripMapper.tripToTripOutputDto(mongoTemplate.save(trip));
    }

    @Override
    public Integer countPassengersOnATrip(String idTrip) {
        Trip trip = mongoTemplate.findById(idTrip, Trip.class);
        if(trip.getPassenger() != null){
            return trip.getPassenger().size();
        } else {
            return 0;
        }
    }

    @Override
    public TripOutputDto changeStatus(String id) {
        TripMapper tripMapper = Mappers.getMapper(TripMapper.class);
        Trip trip = mongoTemplate.findById(id, Trip.class);
        trip.setStatus(!trip.isStatus());
        return tripMapper.tripToTripOutputDto(mongoTemplate.save(trip));
    }

    @Override
    public String getStatus(String id) {
        boolean currentStatus = mongoTemplate.findById(id, Trip.class).isStatus();
        if(currentStatus){
            return "El viaje está disponible";
        } else {
            return "El viaje no está disponible, ha ocurrido una incidencia";
        }
    }
}
