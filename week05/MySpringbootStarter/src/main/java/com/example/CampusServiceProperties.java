package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
public class CampusServiceProperties {

    private CampusService service;

    @Bean
    @ConditionalOnMissingBean
    CampusService exampleService (){
        return  new CampusService(service.getSchool(),service.getKlass(),service.getStudent());
    }

}
