package com.bosonit.formacion.block5profiles;


import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Getter
@Configuration
public class AppConfig {
    @Value("${bd.url}")
    private String url;
}
