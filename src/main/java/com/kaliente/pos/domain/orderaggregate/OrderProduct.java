package com.kaliente.pos.domain.orderaggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.productaggregate.Product;
import com.kaliente.pos.domain.productaggregate.ProductCurrency;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "order_products")
@Table
public class OrderProduct extends BaseEntity {

    @Column(nullable = false, updatable = false)
    private UUID orderedProductId;

    @Column(nullable = false, updatable = false)
    private String orderedProductTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order belongingOrder;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
            @AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
            @AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate")),
            @AttributeOverride( name = "currencyRate", column = @Column(name = "currency_rate"))
    })
    private OrderCurrency currency;

    @Column
    private double price;

    @Column(nullable = true, updatable = true)
    private String invoicePath;

}
