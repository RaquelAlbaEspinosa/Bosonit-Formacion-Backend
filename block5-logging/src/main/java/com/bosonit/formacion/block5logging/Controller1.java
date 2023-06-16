package com.bosonit.formacion.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {

    Logger logger = LoggerFactory.getLogger(Controller1.class);

    @GetMapping("/")
    public String index() {

            logger.info("Esto es info ༼ つ ◕_◕ ༽つ");
            logger.warn("Esto es un WARNING");
            logger.error("Esto es un ERROR");

        return "Vamos a ver que esto funcione";
    }
}
