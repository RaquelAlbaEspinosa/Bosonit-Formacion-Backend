package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @Autowired
    ServicePersona servicePersona;
    @Autowired
    ServiceCiudad ciudadService;

    @GetMapping("/getPersona")
    public Persona getPersona (){
        this.servicePersona.user.setPoblacion(servicePersona.user.getPoblacion());
        this.servicePersona.user.setNombre(servicePersona.user.getNombre());
        this.servicePersona.user.setEdad(servicePersona.user.getEdad() * 2);
        return servicePersona.user;
    }
    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades (){
        return this.ciudadService.ciudades;
    }
}
