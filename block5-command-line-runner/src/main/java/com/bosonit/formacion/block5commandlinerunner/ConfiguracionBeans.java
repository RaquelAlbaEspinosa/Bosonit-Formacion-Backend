package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {
    @Bean
    CommandLineRunner ejecutame(){
        return p -> {
            System.out.println("Ejecución del ComandLineRunner");
        };
    }
    @Bean
    ClaseSecundaria getClaseSecundaria(){
        var s = new ClaseSecundaria() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println(frase);
            }
        };
        return s;
    }
    @Bean
    TerceraClase getTerceraClase(){
        var s = new TerceraClase("Soy la tercera clase (*/ω＼*)") {
            @Override
            public void run(String... args) throws Exception {
                System.out.println(frase);
            }
        };
        return s;
    }

}
