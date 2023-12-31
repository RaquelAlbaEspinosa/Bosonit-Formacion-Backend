package com.bosonit.formacion.block5properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {
    @Bean
    PropertyCharge getPropertyCharge(){

        return new PropertyCharge() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("El valor de greeting es: " + greeting);
                System.out.println("El valor de my.number es: " + number);
                System.out.println("El valor de new.property es: " + property);
            }
        };
    }
}
