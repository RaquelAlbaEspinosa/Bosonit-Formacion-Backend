package com.bosonit.formacion.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ClaseInicial {
    String frase = "Hola desde clase inicial ╰(*°▽°*)╯";
    @PostConstruct
    public void saludo(){
        System.out.println(frase);
    }
}
