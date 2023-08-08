package com.bosonit.formacion.backendFront.feign;

import com.bosonit.formacion.backendFront.controllers.dto.ClientOutputDto;
import com.bosonit.formacion.backendFront.controllers.dto.TripOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://back:8081", name = "tripClientFeign")
public interface TripClientFeign {
    @GetMapping("/client/{id}")
    ClientOutputDto getClientById (@PathVariable String id);
    @GetMapping("/trip/{id}")
    TripOutputDto getTripById (@PathVariable String id);
}
