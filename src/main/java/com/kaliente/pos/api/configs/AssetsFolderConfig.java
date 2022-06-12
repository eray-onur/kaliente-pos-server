package com.kaliente.pos.api.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("assets")
public class AssetsFolderConfig {
    private String personnelProfileImagesPath;
    private String productImagesPath;
    public void setProductImagesPath(String productImagesPath) {
        this.productImagesPath = productImagesPath;
    }
    public String getProductImagesPath() {
        return productImagesPath;
    }

    public void setPersonnelProfileImagesPath(String personnelProfileImagesPath) {
        this.personnelProfileImagesPath = personnelProfileImagesPath;
    }
    public String getPersonnelProfileImagesPath() {
        return personnelProfileImagesPath;
    }
}
