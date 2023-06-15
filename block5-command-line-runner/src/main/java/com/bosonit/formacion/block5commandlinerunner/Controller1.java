package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {
    @Autowired
    ClaseInicial claseInicial;
    @Autowired
    ClaseSecundaria claseSecundaria;
    @Autowired
    TerceraClase terceraClase;

    public Controller1 (ClaseInicial claseInicial, ClaseSecundaria getClaseSecundaria, TerceraClase getTerceraClase) {
        this.claseInicial = claseInicial;
        this.claseSecundaria = getClaseSecundaria;
        this.terceraClase = getTerceraClase;
    }
}
