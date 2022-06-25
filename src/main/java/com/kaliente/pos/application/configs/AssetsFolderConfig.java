package com.kaliente.pos.application.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("assets")
public class AssetsFolderConfig {
    private String personnelProfileImagesPath;
    private String productImagesPath;
}
