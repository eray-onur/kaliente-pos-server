package com.kaliente.pos.application.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Getter
@Setter
public class AppConfig {
    private String mainCurrencyTitle;
}