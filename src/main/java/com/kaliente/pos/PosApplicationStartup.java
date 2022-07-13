package com.kaliente.pos;

import com.kaliente.pos.application.services.AdministrationService;
import com.kaliente.pos.application.services.AuthService;
import com.kaliente.pos.application.services.CurrencyHistoryService;
import com.kaliente.pos.application.services.UserService;
import com.kaliente.pos.domain.useraggregate.Privilege;
import com.kaliente.pos.domain.useraggregate.Role;
import com.kaliente.pos.domain.useraggregate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PosApplicationStartup implements
ApplicationListener<ContextRefreshedEvent> {
	private UserService userService;
	private AdministrationService administrationService;
	private AuthService authService;
	private CurrencyHistoryService currencyHistoryService;

	@Autowired
	public PosApplicationStartup(UserService userService,
								 AdministrationService administrationService,
								 AuthService authService,
								 CurrencyHistoryService currencyHistoryService) {
		this.userService = userService;
		this.administrationService = administrationService;
		this.authService = authService;
		this.currencyHistoryService = currencyHistoryService;
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Check if there are no defined privileges in the system.
		userService.createEssentialPrivileges();
		userService.createEssentialRoles();
		userService.createEssentialUsers();

		// Check if essential currencies exist.
		var currencies = currencyHistoryService.getAllCurrencies();
		if(currencies.isEmpty()) {
			System.out.println("Currency history is empty. " +
					"A currency must be defined or new orders will be impossible to create.");
		}

	}

}
