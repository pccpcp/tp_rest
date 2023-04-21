package com.twic.ProjetIHM;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@ConfigurationProperties(prefix = "com.twic.projetihm")
@Data
public class CustomProperties {

    private String apiUrl;

}
