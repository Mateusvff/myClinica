package com.controleClinica.controleClinicaMedica.config;

import com.controleClinica.controleClinicaMedica.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class DataBaseTestConfig {

    @Autowired
    private DataBaseService dataBaseService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dataBaseService.instantiateDataBase();
        return true;
    }

}
