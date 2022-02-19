package com.kaliente.pos.domain.productaggregate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.kaliente.pos.domain.seedwork.BaseEntity;

@Entity
public class ProductCatalogue extends BaseEntity {
	
	private String title;
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="catalogue")
	private List<Product> products = new ArrayList<Product>();
	
	private ProductCatalogue() {}
	

	
//	@ManyToOne(fetch=FetchType.LAZY, optional = true)
//	private ProductCatalogue parentCatalogue;
//	
//	@OneToMany(mappedBy="parentCatalogue")
//	private List<ProductCatalogue> subcatalogues = new ArrayList<>();


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

//	public ProductCatalogue getParentCatalogue() {
//		return parentCatalogue;
//	}
//
//	public void setParentCatalogue(ProductCatalogue parentCatalogue) {
//		this.parentCatalogue = parentCatalogue;
//	}
//
//	public Set<ProductCatalogue> getSubcatalogues() {
//		return subcatalogues;
//	}
//
//	public void setSubcatalogues(Set<ProductCatalogue> subcatalogues) {
//		this.subcatalogues = subcatalogues;
//	}
}
