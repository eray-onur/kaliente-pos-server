package com.kaliente.pos;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PosApplicationStartup implements
ApplicationListener<ContextRefreshedEvent> {
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
	}

}
