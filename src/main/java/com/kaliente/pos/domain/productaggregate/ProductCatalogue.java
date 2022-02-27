package com.kaliente.pos.domain.productaggregate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity
public class ProductCatalogue extends BaseEntity {
	
	private String title;
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="catalogue")
	private List<Product> products = new ArrayList<Product>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional = true)
	private ProductCatalogue parentCatalogue;
	
	@OneToMany(mappedBy="parentCatalogue")
	private List<ProductCatalogue> subcatalogues = new ArrayList<>();


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCatalogue getParentCatalogue() {
		return parentCatalogue;
	}

	public void setParentCatalogue(ProductCatalogue parentCatalogue) {
		this.parentCatalogue = parentCatalogue;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductCatalogue> getSubcatalogues() {
		return subcatalogues;
	}

	public void setSubcatalogues(List<ProductCatalogue> subcatalogues) {
		this.subcatalogues = subcatalogues;
	}
}
