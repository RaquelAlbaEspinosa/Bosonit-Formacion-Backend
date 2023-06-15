package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public abstract class ClaseSecundaria implements CommandLineRunner {
    String frase = "Hola desde clase secundaria (❁´◡`❁)";
    public void saludo(){
        System.out.println(frase);
    }
}
