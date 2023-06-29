package com.bosonit.formacion.block7crudvalidation.feign;

import com.bosonit.formacion.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@org.springframework.cloud.openfeign.FeignClient(url = "http://localhost:8081", name = "myFeign")
public interface ProfesorFeign {
    @GetMapping("/profesor/{id}")
    ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable String id,
                                   @RequestParam(defaultValue = "simple", required = false) String outputType);
    @GetMapping("getData")
    String getData(URI url);
}
