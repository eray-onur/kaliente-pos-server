package com.kaliente.pos.domain.productaggregate;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.seedwork.AggregateRoot;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="products")
@Table
public class Product extends BaseEntity implements AggregateRoot {
	
	@Column(unique=true)
	private String title;
	@Column
	private String description;
	@Column
	private double price;
	@Column
	private double stockedUnits;
	@Column
	private String imagePath;


	public Product(String title, String description, double price) {
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="catalogue_id", nullable = true)
	private ProductCatalogue catalogue;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ProductCatalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(ProductCatalogue catalogue) {
		this.catalogue = catalogue;
	}

	
}