package com.kaliente.pos.domain.productaggregate;

import java.util.Collection;
import java.util.UUID;

public interface ProductCatalogueRepository {
	ProductCatalogue getProductCatalogueById(UUID catalogueId);
	Collection<ProductCatalogue> getProductCatalogues();
	ProductCatalogue addProductCatalogue(String title, String description, UUID parentCatalogueId);
	ProductCatalogue updateProductCatalogue(UUID catalogueId, String title, String description, UUID parentCatalogueId);
	boolean archiveProductCatalogue(UUID catalogueId);
}
