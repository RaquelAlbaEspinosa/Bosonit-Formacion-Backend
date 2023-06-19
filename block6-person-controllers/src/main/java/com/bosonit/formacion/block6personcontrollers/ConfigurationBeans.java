package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBeans {
    @Bean
    @Qualifier("bean1")
    Persona getPersona1(){
        Persona persona1 = new Persona("Málaga", "Raquel", 28);
        return persona1;
    }
    @Bean
    @Qualifier("bean2")
    Persona getPersona2(){
        Persona persona2 = new Persona("Málaga","Manuel",  27);
        return persona2;
    }
    @Bean
    @Qualifier("bean3")
    Persona getPersona3(){
        Persona persona3 = new Persona("Santiago","Jesús",  30);
        return persona3;
    }
}
