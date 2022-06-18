package com.kaliente.pos.domain.productaggregate;

import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductPrice extends BaseEntity {

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
            @AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
            @AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate")),
            @AttributeOverride( name = "currencyRate", column = @Column(name = "currency_rate"))
    })
    @Column
    private double amount;
}