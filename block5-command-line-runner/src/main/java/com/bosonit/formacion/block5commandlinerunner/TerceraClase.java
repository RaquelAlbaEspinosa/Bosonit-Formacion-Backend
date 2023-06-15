package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public abstract class TerceraClase implements CommandLineRunner {
    String frase;
    public TerceraClase(String frase){
        this.frase = frase;
    }
    public void saludo(){
        System.out.println(this.frase);
    }
}
