package com.kaliente.pos.domain.productaggregate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="product_catalogues")
@Table
@SQLDelete(sql = "update product_catalogues set isActive = 0 where id =?")
@Where(clause = "isActive = 1")
public class ProductCatalogue extends BaseEntity {

	@Column(unique=true)
	private String title;
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="catalogue")
	private List<Product> products = new ArrayList<Product>();
	
	@ManyToOne(fetch=FetchType.LAZY, optional = true, cascade= CascadeType.MERGE)
	@JoinColumn(name = "parent_catalogue_id", nullable = true)
	private ProductCatalogue parentCatalogue;
	
	@OneToMany(mappedBy="parentCatalogue")
	private List<ProductCatalogue> subcatalogues = new ArrayList<>();

	public ProductCatalogue(String title, String description) {
		this.title = title;
		this.description = description;
	}


}
