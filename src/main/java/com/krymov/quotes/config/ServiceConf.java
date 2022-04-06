package com.krymov.quotes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConf {

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
