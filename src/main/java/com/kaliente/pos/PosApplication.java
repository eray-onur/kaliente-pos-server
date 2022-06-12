package com.kaliente.pos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.kaliente.pos.application.models.ApplicationModelMapper;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
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

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(500000);
		return multipartResolver;
	}

}
