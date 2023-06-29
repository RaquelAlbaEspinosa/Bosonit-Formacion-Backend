package com.bosonit.formacion.block6personcontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceCiudad {
    List<Ciudad> ciudades = new ArrayList<>();
    public void addCiudad (Ciudad ciudad){
        this.ciudades.add(ciudad);
    }
}
