package com.spring.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigModelMapper {
    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper mapper=new ModelMapper();
         mapper.getConfiguration().
                setMatchingStrategy(MatchingStrategies.STANDARD);
         return mapper;
    }
}
