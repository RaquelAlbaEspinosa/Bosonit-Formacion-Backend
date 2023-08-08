package com.bosonit.formacion.backend.controller;

import com.bosonit.formacion.backend.application.TripService;
import com.bosonit.formacion.backend.controller.dto.TripInputDto;
import com.bosonit.formacion.backend.controller.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    TripService tripService;
    @PostMapping("/add")
    public TripOutputDto addTrip (@RequestBody TripInputDto tripInputDto){
        return tripService.addTrip(tripInputDto);
    }
    @GetMapping("/{id}")
    public TripOutputDto getTripById (@PathVariable String id){
        return tripService.getTripById(id);
    }
    @GetMapping("/all")
    public Iterable<TripOutputDto> getAllTrip (@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                               @RequestParam(required = false, defaultValue = "4") int pageSize){
        return tripService.getAllTrip(pageNumber, pageSize);
    }
    @PutMapping("/update/{id}")
    public TripOutputDto updateTrip (@PathVariable String id, @RequestBody TripInputDto tripInputDto){
        return tripService.updateTrip(id, tripInputDto);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTripById (@PathVariable String id){
        tripService.deleteTripById(id);
        return "Se ha eliminado el viaje con id: " + id;
    }
    @PutMapping("/addPassenger/{idTrip}/{idClient}")
    public TripOutputDto addPassengerToTrip (@PathVariable String idTrip,
                                             @PathVariable String idClient){
        return tripService.addPassengerToTrip(idTrip, idClient);
    }
    @GetMapping("/count/{idTrip}")
    public Integer countPassengersOnATrip(@PathVariable String idTrip){
        return tripService.countPassengersOnATrip(idTrip);
    }
    @PutMapping("/{idTrip}")
    public TripOutputDto changeStatus (@PathVariable String idTrip){
        return tripService.changeStatus(idTrip);
    }
    @GetMapping("/verify/{idTrip}")
    public String getStatus (@PathVariable String idTrip){
        return tripService.getStatus(idTrip);
    }
}
