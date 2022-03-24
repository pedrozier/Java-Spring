package com.standart.springproject.configuration;

import com.standart.ExternalClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ExternalClass externalClass() {
        ExternalClass externalClass = new ExternalClass();
        return externalClass;
    }

}
