package com.kaliente.pos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.kaliente.pos.application.models.ApplicationModelMapper;


@SpringBootApplication
@EnableAsync
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper mapper = new ModelMapper();
	    
	    new ApplicationModelMapper(mapper);
	    
	    return mapper;
	}

}
