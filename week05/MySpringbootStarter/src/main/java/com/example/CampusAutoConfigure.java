package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CampusService.class)
@EnableConfigurationProperties(CampusServiceProperties.class)
public class CampusAutoConfigure {
    @Autowired
    private CampusServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    CampusService exampleService (){
        return  new CampusService(properties.exampleService().getSchool(), properties.exampleService().getKlass(),properties.exampleService().getStudent());
    }
}
