package com.bosonit.formacion.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public abstract class PropertyCharge implements CommandLineRunner {
    @Value("${greeting}")
    String greeting;
    @Value("${my.number}")
    int number;

    @Value("${new.property}")
    String property;

    public void saludo(){
        System.out.println("hola");
    }
}
