package com.kaliente.pos;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kaliente.pos.application.mappings.DtoMapper;


@SpringBootApplication
@EnableJpaAuditing
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper mapper = new ModelMapper();
	    
	    new DtoMapper(mapper);
	    
	    return mapper;
	}

}
