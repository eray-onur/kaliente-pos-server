package com.kaliente.pos.domain.orderaggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaliente.pos.domain.seedwork.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "order_transactions")
@Table
public class OrderTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order belongingOrder;


    @Enumerated(EnumType.STRING)
    private OrderPaymentMethod paymentMethod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currencyTitle", column = @Column(name = "currency_title")),
            @AttributeOverride( name = "currencyDate", column = @Column(name = "currency_date")),
            @AttributeOverride( name = "baseCrossRate", column = @Column(name = "base_cross_rate")),
            @AttributeOverride( name = "currencyRate", column = @Column(name = "currency_rate"))
    })
    private OrderCurrency paymentCurrency;

    @Column(updatable = false)
    private double paidAmount;

}
