package com.kaliente.pos.domain.productaggregate;


import javax.persistence.*;

import com.kaliente.pos.domain.seedwork.AggregateRoot;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name="products")
@Table
@SQLDelete(sql = "update products set is_deleted = true where id =?")
@Where(clause = "is_deleted = false")
public class Product extends BaseEntity implements AggregateRoot {
	
	@Column(unique=true)
	private String title;
	@Column
	private String description;
	@Column(nullable = true)
	private double stockedUnits;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
			@AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
			@AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate")),
			@AttributeOverride( name = "currencyRate", column = @Column(name = "currency_rate"))
	})
	private ProductCurrency currency;
	@Column
	private double price;
	@Column
	private double cost;
	@Column
	private String imagePath;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="catalogue_id", nullable = true)
	private ProductCatalogue catalogue;

//	@OneToOne(mappedBy = "products")
//	private ProductCost cost;
//
//	@OneToOne(mappedBy = "products")
//	private ProductPrice price;

	
}